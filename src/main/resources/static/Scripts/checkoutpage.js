// Load the products from localStorage
const products = JSON.parse(localStorage.getItem('products')) || []; // Retrieve the updated products

// Get the selected products from localStorage
const selectedProducts = JSON.parse(localStorage.getItem('selectedProducts')) || [];

// Function to display the checkout items
function renderCheckoutItems() {
    const checkoutContainer = document.querySelector('.checkout-items');
    checkoutContainer.innerHTML = ''; // Clear previous content

    let totalPrice = 0;

    selectedProducts.forEach(product => {
        const productDiv = document.createElement('div');
        productDiv.classList.add('checkout-item');
        productDiv.innerHTML = `
            <p><strong>${product.name}</strong></p>
            <p>Price: ${product.price}</p>
            <p>Quantity: ${product.quantity}</p>
            <p>Total: ${product.price * product.quantity}</p>
        `;
        checkoutContainer.appendChild(productDiv);

        // Calculate the total price
        totalPrice += product.price * product.quantity;
    });

    // Display the total price
    const totalPriceDisplay = document.querySelector('.total-price');
    totalPriceDisplay.textContent = totalPrice.toLocaleString();
}

// Function to handle Confirm button click
function handleConfirm() {
    // Retrieve the products array from localStorage (if it exists)
    let products = JSON.parse(localStorage.getItem('products'));

    // Check if selectedProducts exists
    if (selectedProducts && selectedProducts.length > 0) {
        selectedProducts.forEach(selectedProduct => {
            const productIndex = products.findIndex(product => product.name === selectedProduct.name);
            if (productIndex !== -1) {
                // Update the stock of the product based on the quantity in selected products
                products[productIndex].stock -= selectedProduct.quantity;
            }
        });

        // Save the updated products array back to localStorage
        localStorage.setItem('products', JSON.stringify(products));
    }

    // Clear the selected products from localStorage
    localStorage.removeItem('selectedProducts');

    // Optionally, redirect to a confirmation page
    alert('Order Confirmed! Thank you for your purchase.');
    window.location.href = 'PageTransaksi.html'; // Redirect to transaction page or home page
}

// Function to handle Cancel button click
function handleCancel() {
    // Clear the selected products from localStorage
    localStorage.removeItem('selectedProducts');

    // Redirect back to the transaction page
    window.location.href = 'PageTransaksi.html';
}

// Event listener for the Confirm button
document.querySelector('.confirm-btn').addEventListener('click', function() {
    console.log("Confirm button clicked"); // Log when the button is clicked
    handleConfirm();
});

// Event listener for the Cancel button
document.querySelector('.cancel-btn').addEventListener('click', handleCancel);

// Initial rendering of checkout items
renderCheckoutItems();
