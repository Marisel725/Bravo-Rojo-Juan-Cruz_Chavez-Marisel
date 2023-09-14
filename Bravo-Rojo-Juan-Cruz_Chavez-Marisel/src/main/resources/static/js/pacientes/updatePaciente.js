window.addEventListener('load', function () {
    let formularioActualizar = document.querySelector('#formularioPacienteUpdate')
    const id_paciente = document.getElementById('idUpdate')
    const apellido = document.getElementById('apellidoUpdate')
    const nombre = document.getElementById('nombreUpdate')
    const dni = document.getElementById('dniUpdate')
    const fechaIngreso = document.getElementById('fechaIngresoUpdate')

    const id_domicilio = document.getElementById('idUpdateDomicilio')
    const calle = document.getElementById('calleUpdate')
    const numero = document.getElementById('numeroUpdate')
    const localidad = document.getElementById('localidadUpdate')
    const provincia = document.getElementById('provinciaUpdate')

    formularioActualizar.addEventListener('submit', function (event) {
        event.preventDefault();
        const url = 'http://localhost:8082/pacientes/modificar';
        const formData = {
            id: id_paciente.value,
            apellido: apellido.value,
            nombre: nombre.value,
            dni: dni.value,
            fechaIngreso: fechaIngreso.value,
            domicilio: {
                id: id_domicilio.value,
                calle: calle.value,
                numero: numero.value,
                localidad: localidad.value,
                provincia: provincia.value
            }
        };
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
                const pacienteId = data.id;
                let timerInterval
                Swal.fire({
                    icon: 'success',
                    title:'Paciente con ID: '+pacienteId+' ha sido actualizado exitosamente',
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
                    text: 'Error al agregar el Paciente'
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
    const url = "http://localhost:8082/pacientes/buscar/"+id;
    const settings = {
        method: 'GET'
    }
    fetch(url,settings)
        .then(response => response.json())
        .then(data => {
            let paciente = data;
            console.log(paciente)
            document.querySelector('#idUpdate').value = paciente.id

            document.querySelector('#apellidoUpdate').value = paciente.apellido
            document.querySelector('#nombreUpdate').value = paciente.nombre
            document.querySelector('#dniUpdate').value = paciente.dni
            document.querySelector('#fechaIngresoUpdate').value = paciente.fechaIngreso
            document.querySelector('#idUpdateDomicilio').value = paciente.domicilio.id
            document.querySelector('#calleUpdate').value = paciente.domicilio.calle
            document.querySelector('#numeroUpdate').value = paciente.domicilio.numero
            document.querySelector('#localidadUpdate').value = paciente.domicilio.localidad
            document.querySelector('#provinciaUpdate').value = paciente.domicilio.provincia


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
