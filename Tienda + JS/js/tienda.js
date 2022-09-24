function addToChart(productId){
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

      /*let chartCount = document.getElementById('chartCount').textContent;
      chartCount = parseInt(chartCount, 10);
      chartCount++;
      document.getElementById('chartCount').innerHTML = chartCount;
      document.getElementById('chartCount').style.display = "";*/

      // aca se cargaria el html del producto agregado al carrito
      addProductHtmlToAddChart(productId);
      
      Swal.fire(
        'Agregado!',
        'El producto ha sido agregado.',
        'success'
      );

    }
  });
}

function refreshChartCount(){
  let chartProductsCount = localStorage.getItem("chartProductsCount");
  chartProductsCount = parseInt(chartProductsCount, 10);
  document.getElementById('chartCount').innerHTML = chartProductsCount;
  if (chartProductsCount !== 0) {

    // hago visible el iconito con la cantidad de productos agregados
    document.getElementById('chartCount').style.display = "";

    // oculto cartel inicial de NO hay productos
    document.getElementById('noProductChart').style.display = "none";

    // muestro totales
    document.getElementById('productChartTotals').style.display = "";

    // muestro boton de comprar
    document.getElementById('charBuyBtn').style.display = "";

  } else {
    // oculto el iconito con la cantidad de productos agregados
    document.getElementById('chartCount').style.display = "none";

    // oculto totales
    document.getElementById('productChartTotals').style.display = "none";

    // oculto boton de comprar
    document.getElementById('charBuyBtn').style.display = "none";

    // muestro cartel inicial de NO hay productos
    document.getElementById('noProductChart').style.display = "";
  }
}

function deleteProductFromChart(productId) {

  // levanto el array de producto agregados al carrito
  let productsChart = localStorage.getItem("productsChart");
  productsChart = JSON.parse(productsChart);

  // busco la posicion en la que se encuentra el producto a eliminar
  let productChartFound = productsChart.find((element) => element.id === productId);
  let productChartIndex = productsChart.findIndex((element) => element.id === productId);
  
  // lo elimino del array
  productsChart.splice(productChartIndex,1);
  localStorage.setItem("productsChart",JSON.stringify(productsChart));

  // decremento la cantidad de productos del contador
  let chartProductsCount = localStorage.getItem("chartProductsCount");
  chartProductsCount = parseInt(chartProductsCount, 10);
  chartProductsCount = chartProductsCount - productChartFound.count;
  localStorage.setItem("chartProductsCount", chartProductsCount);
  
  //actualizo el monto total
  let chartProductsTotalAmount = localStorage.getItem("chartProductsTotalAmount");
  chartProductsTotalAmount = parseFloat(chartProductsTotalAmount);
  chartProductsTotalAmount = chartProductsTotalAmount - (productChartFound.prize * productChartFound.count);
  localStorage.setItem("chartProductsTotalAmount", chartProductsTotalAmount);
  // muestro el total de dinero
  document.getElementById("chartMoneyTotal").innerHTML = "$" + chartProductsTotalAmount.toLocaleString();

  // actualizo el icono de carrito y oculto o muestro carteles
  refreshChartCount();

  // elimino el bloque de html del producto a eliminar
  document.getElementById("chartProductRow_"+productId).remove();
}

function updateProductCount(productId, value){
    // levanto el array de producto agregados al carrito
  let productsChart = localStorage.getItem("productsChart");
  productsChart = JSON.parse(productsChart);

  // busco la posicion en la que se encuentra el producto
  let productChartFound = productsChart.find((element) => element.id === productId);
  let productChartIndex = productsChart.findIndex((element) => element.id === productId);
  
  // si estoy decrementando y solo me queda 1 elemento no hago nada
  if (value === -1) {
    if (productChartFound.count === 1) {
      return false;
    }
  }

  // incremento o decremento la cantidad del producto
  productChartFound.count = productChartFound.count + value;
  productsChart[productChartIndex] = productChartFound;

  // incremento o decremento la cantidad de productos del contador
  let chartProductsCount = localStorage.getItem("chartProductsCount");
  chartProductsCount = parseInt(chartProductsCount, 10);
  chartProductsCount = chartProductsCount + value;
  localStorage.setItem("chartProductsCount", chartProductsCount);
  
  //actualizo el monto total
  let chartProductsTotalAmount = localStorage.getItem("chartProductsTotalAmount");
  chartProductsTotalAmount = parseFloat(chartProductsTotalAmount);
  chartProductsTotalAmount = chartProductsTotalAmount + (productChartFound.prize * value);
  localStorage.setItem("chartProductsTotalAmount", chartProductsTotalAmount);
  // muestro el total de dinero
  document.getElementById("chartMoneyTotal").innerHTML = "$" + chartProductsTotalAmount.toLocaleString();

  // recalculo el total de unidades y el dinero del producto que acabo de agregar
  document.getElementById("chartProductQty_"+productId).innerHTML = productChartFound.count;
  document.getElementById("chartProductMoneyTotal_"+productId).innerHTML = "$" + (productChartFound.prize * productChartFound.count).toLocaleString();

  // seteo nuevamnete el array en el localStorage
  localStorage.setItem("productsChart",JSON.stringify(productsChart));

  // actualizo el icono de carrito y oculto o muestro carteles
  refreshChartCount();
}

function addProductHtmlToAddChart(productId) {
  
  let productChartFound;
  let productAllFound;

  // busco el producto en el array de todos los productos para obtener sus valores
  let productsAll = localStorage.getItem("products");
  if (productsAll !== null) {
    productsAll = JSON.parse(productsAll);

    // traigo el producto
    productAllFound = productsAll.find((element) => element.id === productId);
    if (productAllFound === undefined) {
      return false;
    }
  } else {
    return false;
  }

  // levanto el array de producto agregados al carrito
  let productsChart = localStorage.getItem("productsChart");
  if (productsChart !== null) {
    productsChart = JSON.parse(productsChart);

    // busco si el producto ya fue agregado
    productChartFound = productsChart.find((element) => element.id === productId);
  }
  
  // incremento la cantidad de productos del contador
  let chartProductsCount = localStorage.getItem("chartProductsCount");
  chartProductsCount = parseInt(chartProductsCount, 10);
  chartProductsCount++;
  localStorage.setItem("chartProductsCount", chartProductsCount);
  
  // actualizo el icono de carrito y oculto o muestro carteles
  refreshChartCount();

  if (productsChart === null || productChartFound === undefined) {

    // creo el objeto del producto a agregar
    var productChart = 
      {
        id : productAllFound.id,
        prize : productAllFound.prize,
        image1 : productAllFound.image1,
        count : 1
      };

    // lo agrego al array
    if (productsChart === null) {
      productsChart = [productChart];
    } else {
      productsChart.push(productChart);
    }
    localStorage.setItem("productsChart",JSON.stringify(productsChart));
    
    // agrego el bloque html del producto
    let htmlRow = 
    '<div class="row" id="chartProductRow_'+productId+'">' +
    '  <div class="col-3">' +
    '    <div><img src="'+productAllFound.image1+'" alt="imagen" width="100" height="100"></div>' +
    '  </div>' +
    '  <div class="col-6">' +
    '    <span>'+productAllFound.name+'</span>' +
    '    <div style="margin-top: 55px;"><span class="myMoneyValueSmall">$'+productAllFound.prize.toLocaleString()+'</span><span style="margin-left: 20px;"> x </span> ' +
    '      <a href="#" onclick="return updateProductCount('+productId+',-1);" class="myChangeProductBtn">&nbsp;<i class="fa-solid fa-minus"></i>&nbsp;</a> ' +
    '      <strong><span id="chartProductQty_'+productId+'">1</span></strong> ' +
    '      <a href="#" onclick="return updateProductCount('+productId+',1);" class="myChangeProductBtn">&nbsp;<i class="fa-solid fa-plus"></i>&nbsp;</a> ' +
    '    </div>' +
    '  </div>' +
    '  <div class="col-3" style="text-align: right;">' +
    '    <button class="btn p-0" onclick="deleteProductFromChart('+productId+');"><i class="fa-solid fa-trash"></i></button>' +
    '    <div style="margin-top: 55px;"><span class="myMoneyValue" id="chartProductMoneyTotal_'+productId+'">$'+productAllFound.prize.toLocaleString()+'</span></div>' +
    '  </div>' +
    '  <hr>' +
    '</div>';
    
    let productChartList = document.getElementById("productChartList").innerHTML;
    document.getElementById("productChartList").innerHTML = productChartList + htmlRow;

    // incremento el total de dinero del carrito
    let chartProductsTotalAmount = localStorage.getItem("chartProductsTotalAmount");
    chartProductsTotalAmount = parseFloat(chartProductsTotalAmount);
    chartProductsTotalAmount = chartProductsTotalAmount + productAllFound.prize;
    localStorage.setItem("chartProductsTotalAmount", chartProductsTotalAmount);
    // muestro el total de dinero
    document.getElementById("chartMoneyTotal").innerHTML = "$" + chartProductsTotalAmount.toLocaleString();

  } else {
    // si entre al else es porque el producto existe ya en el carrito
    
    // busco la posicion de dicho producto en el array del carrito
    let positionProductChart = productsChart.findIndex((element) => element.id === productId);
    
    // incremento la cantidad del producto agregado
    productChartFound.count++;
    productsChart[positionProductChart] = productChartFound;

    // incremento el total de dinero del carrito
    let chartProductsTotalAmount = localStorage.getItem("chartProductsTotalAmount");
    chartProductsTotalAmount = parseFloat(chartProductsTotalAmount);
    chartProductsTotalAmount = chartProductsTotalAmount + productChartFound.prize;
    localStorage.setItem("chartProductsTotalAmount", chartProductsTotalAmount);
    // muestro el total de dinero
    document.getElementById("chartMoneyTotal").innerHTML = "$" + chartProductsTotalAmount.toLocaleString();

    // recalculo el total de unidades y el dinero del producto que acabo de agregar
    document.getElementById("chartProductQty_"+productId).innerHTML = productChartFound.count;
    document.getElementById("chartProductMoneyTotal_"+productId).innerHTML = "$" + (productChartFound.prize * productChartFound.count).toLocaleString();

    // seteo nuevamnete el array en el localStorage
    localStorage.setItem("productsChart",JSON.stringify(productsChart));
  }
}

function buyChart(){

  Swal.fire({
    title: '¿Desea finalizar la compra?',
    text: "Si lo desea puede cancelar!",
    icon: 'warning',
    showCancelButton: true,
    cancelButtonText: 'Cancelar',
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    confirmButtonText: 'Si, comprar!'
  }).then((result) => {
    if (result.isConfirmed) {

      // limpio los datos en el local storage
      initData();

      // vacio la lista de productos del modal
      document.getElementById("productChartList").innerHTML = "";

      // actualizo el icono de carrito y oculto o muestro carteles
      refreshChartCount();

      // cierro el modal
      document.getElementById("closeChart").click();
      
      Swal.fire(
        'Compra exitosa!',
        'Gracias por su compra.',
        'success'
      );

    }
  });
}

function initData(){
  // limpio el local storage
  localStorage.clear();

  // creo variable contadora de productos agregados
  localStorage.setItem("chartProductsCount", 0);

  // creo variable sumadora de importe de productos agregados
  localStorage.setItem("chartProductsTotalAmount", 0);

  // creo array con todos los productos
  const products = [
    {
      id : 1,
      name : "Producto 1",
      prize : parseFloat("250.00"),
      description : "Lorem ipsum dolor sit amet consectetur adipisicing elit. Quo, vel blanditiis repellendus repudiandae fuga sunt in dignissimos nobis, aliquam obcaecati, dicta repellat? Cum culpa ratione aliquid debitis similique porro non.",
      image1 : "images/1-270x350.jpg",
      image2 : "images/2-270x350.jpg",
      image3 : "images/3-270x350.jpg",
      image4 : "images/4-270x350.jpg",
      image5 : "images/5-270x350.jpg"
    },
    {
      id : 2,
      name : "Producto 2",
      prize : parseFloat("850.00"),
      description : "Lorem ipsum dolor sit amet consectetur adipisicing elit. Quo, vel blanditiis repellendus repudiandae fuga sunt in dignissimos nobis, aliquam obcaecati, dicta repellat? Cum culpa ratione aliquid debitis similique porro non.",
      image1 : "images/2-270x350.jpg",
      image2 : "images/1-270x350.jpg",
      image3 : "images/3-270x350.jpg",
      image4 : "images/4-270x350.jpg",
      image5 : "images/5-270x350.jpg"
    },
    {
      id : 3,
      name : "Producto 3",
      prize : parseFloat("1850.00"),
      description : "Lorem ipsum dolor sit amet consectetur adipisicing elit. Quo, vel blanditiis repellendus repudiandae fuga sunt in dignissimos nobis, aliquam obcaecati, dicta repellat? Cum culpa ratione aliquid debitis similique porro non.",
      image1 : "images/3-270x350.jpg",
      image2 : "images/1-270x350.jpg",
      image3 : "images/2-270x350.jpg",
      image4 : "images/4-270x350.jpg",
      image5 : "images/5-270x350.jpg"
    },
    {
      id : 4,
      name : "Producto 4",
      prize : parseFloat("189.50"),
      description : "Lorem ipsum dolor sit amet consectetur adipisicing elit. Quo, vel blanditiis repellendus repudiandae fuga sunt in dignissimos nobis, aliquam obcaecati, dicta repellat? Cum culpa ratione aliquid debitis similique porro non.",
      image1 : "images/4-270x350.jpg",
      image2 : "images/1-270x350.jpg",
      image3 : "images/2-270x350.jpg",
      image4 : "images/3-270x350.jpg",
      image5 : "images/5-270x350.jpg"
    },
    {
      id : 5,
      name : "Producto 5",
      prize : parseFloat("500.00"),
      description : "Lorem ipsum dolor sit amet consectetur adipisicing elit. Quo, vel blanditiis repellendus repudiandae fuga sunt in dignissimos nobis, aliquam obcaecati, dicta repellat? Cum culpa ratione aliquid debitis similique porro non.",
      image1 : "images/5-270x350.jpg",
      image2 : "images/1-270x350.jpg",
      image3 : "images/2-270x350.jpg",
      image4 : "images/3-270x350.jpg",
      image5 : "images/4-270x350.jpg"
    }
  ];

  localStorage.setItem("products",JSON.stringify(products));

}

// inicializo los datos
initData();