window.onload = () => {
    const formulario = document.getElementById("formularioTurno");

    formulario.addEventListener("submit", event => {
        event.preventDefault();
        agregarTurno();

    });


}
function agregarTurno() {

    const fecha = document.getElementById('fecha');
    const hora = document.getElementById('hora');
    const id_paciente = document.getElementById('id_paciente');
    const id_odontologo = document.getElementById('id_odontologo');

    const formData = {
        fechaYhora: `${fecha.value} ${hora.value}`,
        pacienteId: id_paciente.value,
        odontologoId: id_odontologo.value
    };

    const settings = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(formData),
    };

    fetch('http://localhost:8082/turnos/registrar', settings)
        .then(response => {
            if (!response.ok) {
                throw response.status;
            }
            return response.json();
        })
        .then(() => {
            let timerInterval;
            Swal.fire({
                icon: 'success',
                title: 'Turno Creado exitosamente',
                html: 'I will close in <b></b> milliseconds.',
                timer: 2000,
                timerProgressBar: true,
                didOpen: () => {
                    Swal.showLoading();
                    const b = Swal.getHtmlContainer().querySelector('b');
                    timerInterval = setInterval(() => {
                        b.textContent = Swal.getTimerLeft();
                    }, 100);
                },
                willClose: () => {
                    clearInterval(timerInterval);
                    location.reload();
                }
            }).then((result) => {
                /* Read more about handling dismissals below */
                if (result.dismiss === Swal.DismissReason.timer) {
                    console.log('I was closed by the timer');
                }
            });
        })
        .catch(error => {
            console.log(error)
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Error al agregar el turno'
            });
        })
        .finally(() => {
            fecha.value = '';
            hora.value = '';
            id_paciente.value = '';
            id_odontologo.value = '';
        });
}
