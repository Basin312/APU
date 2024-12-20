window.onload = function() {
    loadTransactions();
};

// Function to convert "Dec 2024" to "2024-12"
function getMonthFromString(monthString) {
    const date = new Date(`${monthString} 1, 2024`);  // Add '1' to make it a valid date
    return date.toISOString().slice(0, 7);  // Returns "YYYY-MM"
}

function loadTransactions() {
    // Get the current month from the page (expecting format "MMM YYYY")
    const month = getMonthFromString(document.getElementById('month').innerText.trim());
    console.log(`Loading transactions for: ${month}`);  // Log the month being loaded

    // Retrieve transactions from localStorage using the "YYYY-MM" format
    const transactions = JSON.parse(localStorage.getItem(month)) || [];
    
    console.log('Transactions loaded:', transactions);  // Log the transactions loaded from localStorage

    // Prepare containers to hold data
    let totalIncome = 0;
    let totalExpense = 0;
    let reportContent = document.getElementById('reportContent');

    // Clear any existing content in the report
    reportContent.innerHTML = '';

    // Check if there are any transactions
    if (transactions.length === 0) {
        reportContent.innerHTML = "<p>No transactions found for this month.</p>";
        return;
    }

    // Group transactions by date
    let groupedTransactions = groupTransactionsByDate(transactions);

    // Iterate over each date and display its transactions
    Object.keys(groupedTransactions).forEach(date => {
        let dayContainer = document.createElement('div');
        dayContainer.classList.add('subcontainerlaporan');
        
        // Get the transactions for that date
        let dailyTransactions = groupedTransactions[date];
        let incomeAmount = 0;
        let expenseAmount = 0;

        // Create the header for the day (date and totals for income and expense)
        let dateHeader = document.createElement('div');
        dateHeader.classList.add('subcontainerlaporan-header');

        // Calculate total income and expense for the day
        dailyTransactions.forEach(transaction => {
            if (transaction.type === 'Income') {
                incomeAmount += parseFloat(transaction.amount);
            } else if (transaction.type === 'Expense') {
                expenseAmount += parseFloat(transaction.amount);
            }
        });

        // Append the header content
        dateHeader.innerHTML = `
            <p class="date">${date}</p>
            <p style="color: blue;" class="income">Rp ${formatCurrency(incomeAmount)}</p>
            <p style="color: red;" class="outcome">Rp ${formatCurrency(expenseAmount)}</p>
        `;
        dayContainer.appendChild(dateHeader);

        // Create the body section for the day (list of transactions)
        let dayBody = document.createElement('div');
        dayBody.classList.add('subcontainerlaporan-body');
        
        dailyTransactions.forEach(transaction => {
            let content = document.createElement('div');
            content.classList.add('content');
            content.innerHTML = `
                <p>Kategori: ${transaction.category}</p>
                <p>Rp ${formatCurrency(transaction.amount)}</p>
            `;
            dayBody.appendChild(content);
        });

        // Append the body section to the day container
        dayContainer.appendChild(dayBody);

        // Append the day container to the report content section
        reportContent.appendChild(dayContainer);

        // Update the total income and expense for the entire month
        totalIncome += incomeAmount;
        totalExpense += expenseAmount;
    });

    // Update the total income, expense, and balance for the month
    document.getElementById('totalincome').innerText = `Income: Rp ${formatCurrency(totalIncome)}`;
    document.getElementById('totalekspense').innerText = `Expense: Rp ${formatCurrency(totalExpense)}`;
    document.getElementById('totalselisih').innerText = `Total: Rp ${formatCurrency(totalIncome - totalExpense)}`;
}

// Helper function to group transactions by date
function groupTransactionsByDate(transactions) {
    return transactions.reduce((grouped, transaction) => {
        const date = new Date(transaction.date).toISOString().split('T')[0]; // Format to "YYYY-MM-DD"
        if (!grouped[date]) {
            grouped[date] = [];
        }
        grouped[date].push(transaction);
        return grouped;
    }, {});
}

// Helper function to format currency
function formatCurrency(amount) {
    return new Intl.NumberFormat('id-ID').format(amount); // Using 'id-ID' for Indonesian Rupiah format
}

// Month navigation: Left button (previous month)
document.querySelector('.monthnavigationbutton.left').addEventListener('click', function() {
    // Get the current month and subtract 1 month
    const currentMonth = document.getElementById('month').innerText.trim();
    const newDate = new Date(`${currentMonth} 1, 2024`);
    newDate.setMonth(newDate.getMonth() - 1);
    const newMonth = newDate.toISOString().split('T')[0].slice(0, 7);  // "YYYY-MM"
    document.getElementById('month').innerText = new Date(newMonth + '-01').toLocaleString('en', { month: 'short', year: 'numeric' });
    loadTransactions();
});

// Month navigation: Right button (next month)
document.querySelector('.monthnavigationbutton.right').addEventListener('click', function() {
    // Get the current month and add 1 month
    const currentMonth = document.getElementById('month').innerText.trim();
    const newDate = new Date(`${currentMonth} 1, 2024`);
    newDate.setMonth(newDate.getMonth() + 1);
    const newMonth = newDate.toISOString().split('T')[0].slice(0, 7);  // "YYYY-MM"
    document.getElementById('month').innerText = new Date(newMonth + '-01').toLocaleString('en', { month: 'short', year: 'numeric' });
    loadTransactions();
});
