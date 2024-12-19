document.querySelectorAll(".toggle-details").forEach((button) => {
    button.addEventListener("click", () => {
        const productComponent = button.closest(".productcomponent");
        const productDetails = productComponent.querySelector(".product-details");

        if (productDetails.style.display === "none" || productDetails.style.display === "") {
            productDetails.style.display = "block";
            button.textContent = "⬆";
        } else {
            productDetails.style.display = "none";
            button.textContent = "⬇";
        }
    });
});
