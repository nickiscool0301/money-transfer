-- Insert initial data into the account table
INSERT INTO account (id, name, balance)
VALUES
    (1, 'John Doe', 1000.00),
    (2, 'Jane Smith', 2000.00);

-- Insert initial data into the transaction table
INSERT INTO transaction (id, from_account_id, to_account_id, amount, timestamp, status)
VALUES
    (1, 1, 2, 500.00, NOW(), 'COMPLETED'),
    (2, 2, 1, 300.00, NOW(), 'COMPLETED');