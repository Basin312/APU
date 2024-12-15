
	
DROP TABLE IF EXISTS umk cascade
DROP TABLE IF EXISTS admin cascade
DROP TABLE IF EXISTS transaksi cascade
DROP TABLE IF EXISTS detailtransaksi cascade
DROP TABLE IF EXISTS pembukuan cascade
DROP TABLE IF EXISTS produk cascade

create table umk(idumk serial Primary key, 
					nama varchar(30), 
					password varchar(30), 
					namapemilik varchar(30), 
					nohp varchar(13), 
					alamat varchar(40), 
					deskripsi varchar(255), 
					logo varchar(255), 
					approveUMK boolean);

create table produk(idproduk serial primary key, 
					namaproduk varchar(30), 
					harga decimal(10,2),	
					foto varchar(255),	
					deskripsi varchar(255),	
					satuan varchar(10),	
					id_UMK int references umk(idumk));				

create table transaksi(idtransaksi serial primary key, 
						tanggal date, 
						idumk int references umk(idumk));
						
create table detailtransaksi(nomor serial primary key, 
							idtransaksi int references transaksi(idtransaksi),
							idproduk int references produk(idproduk),
							jumlah int);

create table admin(idadmin serial primary key, 
					nama varchar(30), 
					username varchar(30), 
					password varchar(30), 
					nohp varchar(13));

create table pembukuan(idPembukuan serial primary key,	
						deskripsi varchar(255),
						Tanggal date,
						idUmk int references umk(idumk),
						tipe boolean, --(pengeluaran = 1 / pemasukan = 0 )	
						amount decimal(10,2));


-- Insert UMK records
INSERT INTO umk (nama, password, namapemilik, nohp, alamat, deskripsi, logo, approveumk)
VALUES 
('UMK A', 'password123', 'John Doe', '081234567890', 'Jl. A No. 1', 'Deskripsi UMK A', 'logoA.png', true),
('UMK B', 'password123', 'Jane Smith', '082345678901', 'Jl. B No. 2', 'Deskripsi UMK B', 'logoB.png', true),
('UMK C', 'password123', 'Alice Johnson', '083456789012', 'Jl. C No. 3', 'Deskripsi UMK C', 'logoC.png', true);

-- Insert Produk records for each UMK
INSERT INTO produk (namaproduk, harga, foto, deskripsi, satuan, id_UMK)
VALUES 
('Produk A1', 50000.00, 'produkA1.png', 'Deskripsi Produk A1', 'pcs', 1),  -- id_UMK = 1
('Produk A2', 75000.00, 'produkA2.png', 'Deskripsi Produk A2', 'pcs', 1),  -- id_UMK = 1
('Produk B1', 100000.00, 'produkB1.png', 'Deskripsi Produk B1', 'pcs', 2),  -- id_UMK = 2
('Produk B2', 120000.00, 'produkB2.png', 'Deskripsi Produk B2', 'pcs', 2),  -- id_UMK = 2
('Produk C1', 90000.00, 'produkC1.png', 'Deskripsi Produk C1', 'pcs', 3),  -- id_UMK = 3
('Produk C2', 110000.00, 'produkC2.png', 'Deskripsi Produk C2', 'pcs', 3);  -- id_UMK = 3

-- Insert Transaksi records for each UMK
INSERT INTO transaksi (tanggal, idumk)
VALUES 
('2024-11-01', 1),  -- UMK A
('2024-11-05', 2),  -- UMK B
('2024-11-10', 3);  -- UMK C

-- Insert DetailTransaksi records, associating with Produk and Transaksi
INSERT INTO detailtransaksi (idtransaksi, idproduk, jumlah)
VALUES 
(1, 1, 2),  -- Transaksi 1 (UMK A) - Produk A1 (2 pcs)
(1, 2, 1),  -- Transaksi 1 (UMK A) - Produk A2 (1 pcs)
(2, 3, 5),  -- Transaksi 2 (UMK B) - Produk B1 (5 pcs)
(2, 4, 3),  -- Transaksi 2 (UMK B) - Produk B2 (3 pcs)
(3, 5, 4),  -- Transaksi 3 (UMK C) - Produk C1 (4 pcs)
(3, 6, 2);  -- Transaksi 3 (UMK C) - Produk C2 (2 pcs)

-- Insert UMK records
INSERT INTO umk (nama, password, namapemilik, nohp, alamat, deskripsi, logo, approveumk)
VALUES 
('UMK D', 'password456', 'Mike Brown', '084567890123', 'Jl. D No. 4', 'Deskripsi UMK D', 'logoD.png', false),
('UMK E', 'password456', 'Sara Wilson', '085678901234', 'Jl. E No. 5', 'Deskripsi UMK E', 'logoE.png', false),
('UMK F', 'password456', 'David Lee', '086789012345', 'Jl. F No. 6', 'Deskripsi UMK F', 'logoF.png', false);

-- Insert Produk records for each UMK
INSERT INTO produk (namaproduk, harga, foto, deskripsi, satuan, id_UMK)
VALUES 
('Produk D1', 150000.00, 'produkD1.png', 'Deskripsi Produk D1', 'pcs', 4),  -- id_UMK = 4
('Produk D2', 200000.00, 'produkD2.png', 'Deskripsi Produk D2', 'pcs', 4),  -- id_UMK = 4
('Produk E1', 120000.00, 'produkE1.png', 'Deskripsi Produk E1', 'pcs', 5),  -- id_UMK = 5
('Produk E2', 180000.00, 'produkE2.png', 'Deskripsi Produk E2', 'pcs', 5),  -- id_UMK = 5
('Produk F1', 90000.00, 'produkF1.png', 'Deskripsi Produk F1', 'pcs', 6),  -- id_UMK = 6
('Produk F2', 130000.00, 'produkF2.png', 'Deskripsi Produk F2', 'pcs', 6);  -- id_UMK = 6

-- Insert Transaksi records for each UMK
INSERT INTO transaksi (tanggal, idumk)
VALUES 
('2024-11-15', 4),  -- UMK D
('2024-11-18', 5),  -- UMK E
('2024-11-20', 6);  -- UMK F

-- Insert DetailTransaksi records, associating with Produk and Transaksi
INSERT INTO detailtransaksi (idtransaksi, idproduk, jumlah)
VALUES 
(1, 1, 3),  -- Transaksi 1 (UMK D) - Produk D1 (3 pcs)
(1, 2, 2),  -- Transaksi 1 (UMK D) - Produk D2 (2 pcs)
(2, 3, 4),  -- Transaksi 2 (UMK E) - Produk E1 (4 pcs)
(2, 4, 1),  -- Transaksi 2 (UMK E) - Produk E2 (1 pcs)
(3, 5, 6),  -- Transaksi 3 (UMK F) - Produk F1 (6 pcs)
(3, 6, 3);  -- Transaksi 3 (UMK F) - Produk F2 (3 pcs)