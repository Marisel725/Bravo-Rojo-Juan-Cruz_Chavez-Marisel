window.addEventListener('load', function () {
    const url= 'http://localhost:8082/turnos/listar';
    const settings = {
        method: 'GET'
    }
    actualizarTabla();

    function actualizarTabla(){

        fetch(url,settings)
            .then(response => response.json())
            .then(data => {
                for(turno of data) {
                    console.log(data)
                    let table = document.getElementById("odontologoTable");
                    let turnoRow = table.insertRow();
                    let tr_id = 'tr_' + turno.id;
                    turnoRow.id = tr_id;
                    const [fecha,hora] = turno.fechaYHora.split('T')

                    let botonActualizar = '<button ' +
                        ' id=' + '\"' + 'btn_id_' + turno.id + '\"' +
                        ' type="button" onclick="findBy('+ turno.id +')" data-bs-toggle="modal" data-bs-target="#exampleModal2" class="mx-2 btn btn-sm btn-primary"><i class="fa-solid fa-pencil"></i></button>';


                    let botonBorrar = '<button' +
                        ' id=' + '\"' + 'btn_delete_' + turno.id + '\"' +
                        ' type="button" onclick="deleteBy('+turno.id +')"class="btnBorrar btn btn-sm btn-danger"><i class="fa-solid fa-trash-can"></i></button>';

                    turnoRow.innerHTML =
                        // '<td >' + botonActualizar + '</td>' +
                        // '<td class=\"td_id\">' + odontologo.id.toUpperCase() + '</td>' +
                        '<td class=\"td_id\">' + turno.id + '</td>' +
                        '<td class=\"td_fecha_turno\">' + fecha + '</td>'+
                        '<td class=\"td_hora_turno\">' + hora + '</td>'+
                        '<td class=\"td_id_paciente\">' + turno.pacienteTurnoSalidaDto.id+ '</td>'+
                        '<td class=\"td_paciente_apellido\">' + turno.pacienteTurnoSalidaDto.apellido.toUpperCase() + '</td>'+
                        '<td class=\"td_id_odontologo\">' + turno.odontologoTurnoSalidaDto.id + '</td>'+
                        '<td class=\"td_apellido_odontologo\">' + turno.odontologoTurnoSalidaDto.apellido.toUpperCase() + '</td>'+
                        '<td >'+ botonActualizar + botonBorrar + '</td>' ;

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
                    errorMessage = 'Algo salió mal al obtener los Turnos!'
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