

window.onload = () => {
    const formulario = document.getElementById("formularioPaciente");

    formulario.addEventListener("submit", event => {
        event.preventDefault();
        agregarPaciente();

    });

}

function agregarPaciente() {
    const apellido = document.getElementById('apellido')
    const nombre = document.getElementById('nombre')
    const dni = document.getElementById('dni')
    const fechaIngreso = document.getElementById('fechaIngreso')
    const calle = document.getElementById('calle')
    const numero = document.getElementById('numero')
    const localidad = document.getElementById('localidad')
    const provincia = document.getElementById('provincia')

    const formData = {
        apellido: apellido.value,
        nombre: nombre.value,
        dni: dni.value,
        fechaIngreso: fechaIngreso.value,
        domicilio: {
            calle: calle.value,
            numero: numero.value,
            localidad: localidad.value,
            provincia: provincia.value
        }
    }

    const settings = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(formData),
    };

    fetch('http://localhost:8082/pacientes/registrar', settings)
        .then(response => {
            if (!response.ok) {
                throw response.status
            }
            return response.json()
        })
        .then(() => {
            let timerInterval
            Swal.fire({
                icon: 'success',
                title:'Paciente Creado exitosamente',
                html: 'I will close in <b></b> milliseconds.',
                timer: 2000,
                timerProgressBar: true,
                didOpen: () => {
                    Swal.showLoading()
                    const b = Swal.getHtmlContainer().querySelector('b')
                    timerInterval = setInterval(() => {
                        b.textContent = Swal.getTimerLeft()
                    }, 100)
                },
                willClose: () => {
                    clearInterval(timerInterval)
                    location.reload();
                }
            }).then((result) => {

                /* Read more about handling dismissals below */
                if (result.dismiss === Swal.DismissReason.timer) {
                    console.log('I was closed by the timer')
                }
            })
        })
        .catch(error => {
            console.log(error)
            let errorMessage = ''
            let icon = ''
            if (error === 401) {
                errorMessage = 'Su usuario no está autorizado para realizar esta acción.'
                icon = 'warning'
            } else if (error === 403) {
                errorMessage = 'Su usuario no está autorizado para realizar esta acción.'
                icon = 'warning'
            } else {
                errorMessage = 'Algo salió mal al guardar el Odontólogo!'
                icon= 'error'
            }
            Swal.fire({
                icon: icon,
                title: 'Oops...',
                text: errorMessage
            })
        })
        .finally(() => {
            nombre.value = ''
            apellido.value = ''
        })
}