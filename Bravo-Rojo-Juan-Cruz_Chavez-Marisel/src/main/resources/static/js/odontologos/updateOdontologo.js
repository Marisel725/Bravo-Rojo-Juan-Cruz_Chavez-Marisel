window.addEventListener('load', function () {
    let formularioActualizar = document.querySelector('#formularioOdontologoUpdate')


    formularioActualizar.addEventListener('submit', function (event) {
        event.preventDefault();
        const url = 'http://localhost:8082/odontologos/modificar';
        const formData = {
            id: document.querySelector('#idUpdate').value,
            matricula: document.querySelector('#numeroMatriculaUpdate').value,
            nombre: document.querySelector('#nombreUpdate').value,
            apellido: document.querySelector('#apellidoUpdate').value,
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
                const odontologoId = data.id
                let timerInterval
                Swal.fire({
                    icon: 'success',
                    title:'Odontologo con ID: '+odontologoId+' ha sido actualizado exitosamente',
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
                    text: 'Error al agregar el odontologo'
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
    const url = "http://localhost:8082/odontologos/buscar/"+id;
    const settings = {
        method: 'GET'
    }
    fetch(url,settings)
        .then(response => response.json())
        .then(data => {
            let odontologo = data;
            console.log(odontologo)
            document.querySelector('#idUpdate').value = odontologo.id

            document.querySelector('#numeroMatriculaUpdate').value = odontologo.matricula
            document.querySelector('#nombreUpdate').value = odontologo.nombre;
            document.querySelector('#apellidoUpdate').value = odontologo.apellido;
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
