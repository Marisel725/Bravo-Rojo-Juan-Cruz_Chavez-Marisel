

window.onload = () => {
    const formulario = document.getElementById("formularioOdontologo");

    formulario.addEventListener("submit", event => {
        event.preventDefault();
        agregarOdontologo();

    });

}

function agregarOdontologo() {
    const nombre = document.getElementById("nombre");
    const apellido = document.getElementById("apellido");
    const numeroMatricula = document.getElementById("numeroMatricula");


    const formData = {
        nombre: nombre.value,
        apellido: apellido.value,
        matricula: numeroMatricula.value,
    };

    const settings = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(formData),
    };

    fetch('http://localhost:8082/odontologos/registrar', settings)
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
                title:'Odontologo Creado exitosamente',
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
            let errorMessage = ''
            let icon = ''
            if (error === 401) {
                errorMessage = 'Su usuario no está autorizado para realizar esta acción.'
                icon = 'warning'
            } else if (error === 403) {
                errorMessage = 'Su usuario no está autorizado para realizar esta acción.'
                icon = 'warning'
            } else {
                errorMessage = `${error.message}`
                icon= 'error'
            }
            Swal.fire({
                icon: icon,
                title: 'Oops...',
                text: errorMessage
            })
        })
        .finally(() => {
            numeroMatricula.value = ''
            nombre.value = ''
            apellido.value = ''
        })
}