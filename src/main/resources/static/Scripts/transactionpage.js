// Mockup data
const products = [
    { name: 'Produk D1', price: 150000.0, stock: 10, initialStock: 10, quantity: 0 },
    { name: 'Produk D2', price: 200000.0, stock: 10, initialStock: 10, quantity: 0 },
    { name: 'Produk E1', price: 120000.0, stock: 10, initialStock: 10, quantity: 0 },
    { name: 'Produk E2', price: 180000.0, stock: 10, initialStock: 10, quantity: 0 },
    { name: 'Produk F1', price: 90000.0, stock: 10, initialStock: 10, quantity: 0 },
    { name: 'Produk F2', price: 130000.0, stock: 10, initialStock: 10, quantity: 0 },
];

let totalProducts = 0;
let filteredProducts = [...products]; // Start with all products

// Function to change the product quantity and update stock
function changeQuantity(event, change) {
    const index = event.target.dataset.index;
    const product = filteredProducts[index];

    if (change === 1 && product.stock > 0) {
        product.quantity++;
        product.stock--;
        totalProducts++;
    } else if (change === -1 && product.quantity > 0) {
        product.quantity--;
        product.stock++;
        totalProducts--;
    }

    renderCatalog();
    updateTotalProducts();
}

// Function to reset the quantities and stock
function resetTransaction() {
    filteredProducts.forEach(product => {
        product.quantity = 0; // Reset the quantity only
    });

    totalProducts = 0; // Reset the total products
    renderCatalog(); // Re-render the product catalog
    updateTotalProducts(); // Update the total display
}

// Function to render the product catalog
function renderCatalog() {
    const catalog = document.querySelector('.product-catalog');
    catalog.innerHTML = '';

    filteredProducts.forEach((product, index) => {
        const productDiv = document.createElement('div');
        productDiv.classList.add('productcomponent');
        productDiv.innerHTML = `
            <div class="product-summary">
                <p class="product-name">${product.name}</p>
                <p class="product-stock">Stock: ${product.stock}</p>
                <div class="product-counter">
                    <button class="decrease-btn" data-index="${index}" ${product.quantity === 0 ? 'disabled' : ''}>-</button>
                    <span class="product-quantity">${product.quantity}</span>
                    <button class="increase-btn" data-index="${index}" ${product.stock === 0 ? 'disabled' : ''}>+</button>
                </div>
            </div>
        `;
        catalog.appendChild(productDiv);
    });

    document.querySelectorAll('.decrease-btn').forEach((btn) =>
        btn.addEventListener('click', (e) => changeQuantity(e, -1))
    );

    document.querySelectorAll('.increase-btn').forEach((btn) =>
        btn.addEventListener('click', (e) => changeQuantity(e, 1))
    );
}

// Function to update the "Total products" display
function updateTotalProducts() {
    const totalDisplay = document.querySelector('.total-products');
    totalDisplay.textContent = `Total products: ${totalProducts}`;
}

// Function to filter products based on search input
function searchProducts() {
    const searchTerm = document.querySelector('#search-bar').value.toLowerCase();

    filteredProducts = products.filter(product =>
        product.name.toLowerCase().includes(searchTerm)
    );

    renderCatalog(); // Re-render the filtered products

    
}

function loadProducts() {
    // Retrieve the products array from localStorage (if it exists)
    const storedProducts = JSON.parse(localStorage.getItem('products'));

    if (storedProducts) {
        // Update the main products array with the stored products
        products.splice(0, products.length, ...storedProducts);  // Clear and update the array in one step
    }

    // Re-render the catalog with updated products
    filteredProducts = [...products]; // Reset filteredProducts to reflect the loaded products
    renderCatalog();
    updateTotalProducts();
}

document.querySelector('.proceed-btn').addEventListener('click', () => {
    // Save the products with quantities to localStorage
    const selectedProducts = products.filter(product => product.quantity > 0);
    localStorage.setItem('selectedProducts', JSON.stringify(selectedProducts));

    // Save the updated products with stock changes to localStorage
    localStorage.setItem('products', JSON.stringify(products)); // Save products as well

    // Redirect to checkout page
    window.location.href = 'checkoutpage.html'; // Make sure this URL is correct
});

// Add event listener to the search bar
document.querySelector('#search-bar').addEventListener('input', searchProducts);

// Add event listener to Cancel button
document.querySelector('.cancel-btn').addEventListener('click', resetTransaction);

// Initial rendering
loadProducts(); 
renderCatalog();
updateTotalProducts();

