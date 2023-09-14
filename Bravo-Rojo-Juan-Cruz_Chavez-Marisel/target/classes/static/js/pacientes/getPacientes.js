window.addEventListener('load', function () {
    const url= 'http://localhost:8082/pacientes/listar';
    const settings = {
        method: 'GET'
    }
    actualizarTabla();

    function actualizarTabla(){
        fetch(url,settings)
            .then(response => {
                if (!response.ok) {
                    throw response.status
                }
                return response.json()
            })
            .then(data => {
                console.log(data)
                for(paciente of data) {
                    console.log(paciente)
                    console.log(paciente.id)
                    let table = document.getElementById("odontologoTable");
                    console.log(table)
                    let odontologoRow = table.insertRow();
                    let tr_id = 'tr_' + paciente.id;
                    console.log(tr_id)
                    odontologoRow.id = tr_id;
                    console.log(odontologoRow)

                    let botonActualizar = '<button ' +
                        ' id=' + '\"' + 'btn_id_' + paciente.id + '\"' +
                        ' type="button" onclick="findBy('+paciente.id+')" data-bs-toggle="modal" data-bs-target="#exampleModal2" class="mx-2 btn btn-sm btn-primary"><i class="fa-solid fa-pencil"></i></button>';


                    let botonBorrar = '<button' +
                        ' id=' + '\"' + 'btn_delete_' + paciente.id + '\"' +
                        ' type="button" onclick="deleteBy('+paciente.id+')"class="btnBorrar btn btn-sm btn-danger"><i class="fa-solid fa-trash-can"></i></button>';
                    console.log(paciente.fechaIngreso)
                    odontologoRow.innerHTML =
                        '<td class=\"td_id\">' + paciente.id + '</td>' +
                        '<td class=\"td_apellido\">' + paciente.apellido.toUpperCase() + '</td>' +
                        '<td class=\"td_nombre\">' + paciente.nombre.toUpperCase() + '</td>' +
                        '<td class=\"td_email\">' + paciente.dni+ '</td>' +
                        '<td class=\"td_email\">' + paciente.fechaIngreso + '</td>' +
                        '<td class=\"td_domicilio_id\">' + paciente.domicilio.id + '</td>' +
                        '<td class=\"td_domicilio_calle\">' + paciente.domicilio.calle.toUpperCase() + '</td>' +
                        '<td class=\"td_domicilio_numero\">' + paciente.domicilio.numero + '</td>' +
                        '<td class=\"td_domicilio_localidad\">' + paciente.domicilio.localidad.toUpperCase() + '</td>' +
                        '<td class=\"td_domicilio_provincia\">' + paciente.domicilio.provincia.toUpperCase() + '</td>' +
                        '<td>' + botonActualizar + botonBorrar + '</td>';

                    console.log(odontologoRow.innerHTML)
                }

            })
            .catch(error => {
                console.error(error); // Agrega esto para ver el error en la consola
                let errorMessage = ''
                let icon = ''
                if (error === 401) {
                    errorMessage = 'Su usuario no está autorizado para realizar esta acción.'
                    icon = 'warning'
                } else if (error === 403) {
                    errorMessage = 'Su usuario no está autorizado para realizar esta acción.'
                    icon = 'warning'
                } else {
                    errorMessage = 'Algo salió mal al obtener los Pacientes!'
                    icon= 'error'
                }
                Swal.fire({
                    icon: icon,
                    title: 'Oops...',
                    text: errorMessage
                })
            });

    }


})