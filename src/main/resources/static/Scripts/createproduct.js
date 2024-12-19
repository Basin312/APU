document.getElementById("edit-title-btn").addEventListener("click", function () {
    const titleElement = document.getElementById("product-title");
    const currentText = titleElement.textContent;

    // Replace title with input field
    const inputField = document.createElement("input");
    inputField.type = "text";
    inputField.value = currentText;
    inputField.id = "title-input";

    titleElement.replaceWith(inputField);
    this.textContent = "Save";

    // Save functionality
    this.addEventListener("click", function saveTitle() {
        const newTitle = inputField.value || "New Product";
        const newTitleElement = document.createElement("h1");
        newTitleElement.id = "product-title";
        newTitleElement.textContent = newTitle;

        inputField.replaceWith(newTitleElement);
        this.textContent = "Edit";
        this.removeEventListener("click", saveTitle);
    });
});
