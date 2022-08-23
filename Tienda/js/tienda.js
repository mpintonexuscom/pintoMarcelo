function addToChart(){
  Swal.fire({
    title: '¿Desea agregar el producto al carrito?',
    text: "Podras quitarlo luego si lo deseas!",
    icon: 'warning',
    showCancelButton: true,
    cancelButtonText: 'Cancelar',
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    confirmButtonText: 'Si, agregar!'
  }).then((result) => {
    if (result.isConfirmed) {

      let chartCount = document.getElementById('chartCount').textContent;
      chartCount = parseInt(chartCount, 10);
      chartCount++;
      document.getElementById('chartCount').innerHTML = chartCount;
      document.getElementById('chartCount').style.display = "";

      Swal.fire(
        'Agregado!',
        'El producto ha sido agregado.',
        'success'
      )
    }
  });
}