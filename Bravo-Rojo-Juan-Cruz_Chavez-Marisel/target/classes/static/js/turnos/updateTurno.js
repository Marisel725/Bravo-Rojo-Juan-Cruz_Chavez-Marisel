window.addEventListener('load', function () {
    let formularioActualizar = document.querySelector('#formularioTurnoUpdate')
    const id = document.getElementById('idUpdate')
    const fecha = document.getElementById('fechaUpdate')
    const hora = document.getElementById('horaUpdate')
    const id_paciente = document.getElementById('id_pacienteUpdate')
    const id_odontologo = document.getElementById('id_odontolotoUpdate')


    formularioActualizar.addEventListener('submit', function (event) {
        event.preventDefault();
        const url = 'http://localhost:8082/turnos/modificar';
        const formData = {
            id: id.value,
            fechaYhora: `${fecha.value}T${hora.value}`,
            pacienteId : id_paciente.value,
            odontologoId: id_odontologo.value

        }
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        fetch(url, settings)
            .then(response => {
                if (!response.ok) {
                    throw response.status
                }
                return response.json()
            })
            .then((data) => {
                const turnoId = data.id
                let timerInterval
                Swal.fire({
                    icon: 'success',
                    title:'Turno con ID: '+turnoId+' ha sido actualizado exitosamente',
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

                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'Error al actualizar el Turno. Verifica que el ID del Odontologo o el Paciente exista'
                })
            })
            .finally(() => {
                numeroMatricula.value = ''
                nombre.value = ''
                apellido.value = ''
            })
    })


})



function findBy(id) {
    const url = "http://localhost:8082/turnos/buscar/"+id;
    const settings = {
        method: 'GET'
    }
    fetch(url,settings)
        .then(response => response.json())
        .then(data => {
            let turno = data;
            const [fecha,hora] = turno.fechaYHora.split('T')
            console.log(turno)
            document.querySelector('#idUpdate').value = turno.id
            document.querySelector('#fechaUpdate').value = fecha;
            document.querySelector('#horaUpdate').value = hora;
            document.querySelector('#id_pacienteUpdate').value = turno.pacienteTurnoSalidaDto.id;
            document.querySelector('#id_odontolotoUpdate').value = turno.odontologoTurnoSalidaDto.id;

            //el formulario por default esta oculto y al editar se habilita
            //el formulario por default esta oculto y al editar se habilita
            // let formularioActualizar = document.querySelector('#div_odontologo_updating')
            // toggleDisplay(formularioActualizar)
        }).catch(error => {
        alert("Error: " + error);
    })

    // const toggleDisplay = target => target.style.display =
    //     (target.style.display == 'none') ?
    //         'block' :
    //         'none'
}
