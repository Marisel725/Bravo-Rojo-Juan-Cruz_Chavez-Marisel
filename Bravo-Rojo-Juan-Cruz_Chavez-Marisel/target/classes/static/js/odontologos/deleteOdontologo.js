function deleteBy(id) {

    const url = 'http://localhost:8082/odontologos/eliminar/' + id
    const settings = {
        method: 'DELETE'
    }

    Swal.fire({
        title: 'Estas seguro?',
        text: '¡No podrás revertir este cambio! Todos los turnos asociados a este odontologo seran borrados.',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Si, borrar!'
    }).then(result => {
        if (result.isConfirmed) {
            fetch(url, settings).then(response => {
                if (response.status === 200) {
                    let timerInterval
                    Swal.fire({
                        icon: 'success',
                        title:'Odontologo borrado exitosamente',
                        text:'Odontologo con ID:'+id+ 'ha sido borrado exitosamente',
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
                        }
                    }).then((result) => {

                        /* Read more about handling dismissals below */
                        if (result.dismiss === Swal.DismissReason.timer) {
                            console.log('I was closed by the timer')
                            let row_id = "#tr_" + id;
                            document.querySelector(row_id).remove();
                            location.reload();
                        }
                    })

                } else
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: 'Algo salió mal!'
                    })
            })
        }
    })
}

// function deleteBy(id)
// {
//     //con fetch invocamos a la API de peliculas con el método DELETE
//     //pasandole el id en la URL
//     const url = '/odontologo/'+ id;
//     const settings = {
//         method: 'DELETE'
//     }
//     fetch(url,settings)
//         .then(response => response.json())
//
//     //borrar la fila de la pelicula eliminada
//
//
// }