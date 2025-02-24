CREATE DATABASE Aluguel;

--Após criar o banco, acesse a Query Tool do mesmo e insira os comandos restante:

-- Clientes
CREATE TABLE cliente (
	id_cliente SERIAL PRIMARY KEY,
    nome VARCHAR(45) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    telefone VARCHAR(11) NOT NULL,
    endereco VARCHAR(60)
);

-- Equipamentos
CREATE TABLE equipamento (
	id_equip SERIAL PRIMARY KEY,
    descricao VARCHAR(60) NOT NULL,
    vlr_diaria DOUBLE PRECISION NOT NULL,
    vlr_mensal DOUBLE PRECISION NOT NULL,
    qtd_total INT NOT NULL,
    qtd_disponivel INT NOT NULL DEFAULT 0
);

-- Trigger para auto-preencher a quantidade disponivel do equipamento ao realizar o cadastro
CREATE OR REPLACE FUNCTION preencher_qtd_disponivel()
RETURNS TRIGGER AS $$
BEGIN
    NEW.qtd_disponivel = NEW.qtd_total;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_preencher_qtd_disponivel
BEFORE INSERT ON equipamento
FOR EACH ROW
EXECUTE FUNCTION preencher_qtd_disponivel();

-- Contratos
CREATE TABLE contrato (
    id_contrato SERIAL PRIMARY KEY,
	tipo INT NOT NULL,
    id_cliente INT,
    id_equip INT,
	qtd_equip INT NOT NULL,
    data_inicio DATE NOT NULL,
    data_fim DATE NOT NULL,
    data_entrega DATE,
    status VARCHAR(1) NOT NULL DEFAULT 'A',
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente) ON DELETE SET NULL,
    FOREIGN KEY (id_equip) REFERENCES equipamento(id_equip) ON DELETE SET NULL
);

-- Trigger para auto-atualizar a quantidade disponivel do equipamento após realizar o aluguel
CREATE OR REPLACE FUNCTION atualizar_qtd_disponivel()
RETURNS TRIGGER AS $$
BEGIN
    UPDATE equipamento
    SET qtd_disponivel = qtd_disponivel - NEW.qtd_equip
    WHERE id_equip = NEW.id_equip;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_atualizar_qtd_disponivel
AFTER INSERT ON contrato
FOR EACH ROW
EXECUTE FUNCTION atualizar_qtd_disponivel();

-- Trigger para auto-atualizar a quantidade disponivel do equipamento após finalizar o aluguel
CREATE OR REPLACE FUNCTION devolver_qtd_disponivel()
RETURNS TRIGGER AS $$
BEGIN
    UPDATE equipamento
    SET qtd_disponivel = qtd_disponivel + OLD.qtd_equip
    WHERE id_equip = OLD.id_equip;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_devolver_qtd_disponivel
AFTER UPDATE ON contrato
FOR EACH ROW
EXECUTE FUNCTION devolver_qtd_disponivel();

-- Trigger para auto-ajustar a quantidade disponivel quando a quantidade total for alterada
CREATE OR REPLACE FUNCTION ajustar_qtd_disponivel()
RETURNS TRIGGER AS $$
BEGIN
    IF OLD.qtd_total <> NEW.qtd_total THEN
        NEW.qtd_disponivel = OLD.qtd_disponivel + (NEW.qtd_total - OLD.qtd_total);
        IF NEW.qtd_disponivel < 0 THEN
            NEW.qtd_disponivel = 0;
        END IF;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_ajustar_qtd_disponivel
BEFORE UPDATE ON equipamento
FOR EACH ROW
EXECUTE FUNCTION ajustar_qtd_disponivel();

-- Totalização
CREATE TABLE totalizacao (
	id_tot SERIAL PRIMARY KEY,
	id_contrato INT NOT NULL UNIQUE,
    valor DOUBLE PRECISION NOT NULL,
	multa DOUBLE PRECISION,
	juros DOUBLE PRECISION,
	vlr_total DOUBLE PRECISION NOT NULL,
	FOREIGN KEY (id_contrato) REFERENCES contrato(id_contrato)
);

INSERT INTO equipamento(descricao, vlr_diaria, vlr_mensal, qtd_total) VALUES ('Betoneira 110v Eletrolux 100kg', 100.00, 2500.00, 10);
INSERT INTO equipamento(descricao, vlr_diaria, vlr_mensal, qtd_total) VALUES ('Furadeira de Impacto Bosch 500W', 50.00, 1200.00, 15);
INSERT INTO equipamento(descricao, vlr_diaria, vlr_mensal, qtd_total) VALUES ('Serra Circular Makita 1400W', 80.00, 2000.00, 8);
INSERT INTO equipamento(descricao, vlr_diaria, vlr_mensal, qtd_total) VALUES ('Andaime Modular 2m', 150.00, 3500.00, 5);
INSERT INTO equipamento(descricao, vlr_diaria, vlr_mensal, qtd_total) VALUES ('Gerador a Gasolina 5.5KVA', 200.00, 5000.00, 3);
INSERT INTO equipamento(descricao, vlr_diaria, vlr_mensal, qtd_total) VALUES ('Martelete Rompedor Dewalt 10kg', 120.00, 2800.00, 7);
INSERT INTO equipamento(descricao, vlr_diaria, vlr_mensal, qtd_total) VALUES ('Compactador de Solo a Gasolina', 180.00, 4500.00, 4);
INSERT INTO equipamento(descricao, vlr_diaria, vlr_mensal, qtd_total) VALUES ('Cortadora de Piso Norton Clipper 2HP', 130.00, 3200.00, 6);
INSERT INTO equipamento(descricao, vlr_diaria, vlr_mensal, qtd_total) VALUES ('Misturador de Argamassa 1200W', 70.00, 1700.00, 10);
INSERT INTO equipamento(descricao, vlr_diaria, vlr_mensal, qtd_total) VALUES ('Placa Vibratória Honda 90kg', 160.00, 4200.00, 5);
INSERT INTO equipamento(descricao, vlr_diaria, vlr_mensal, qtd_total) VALUES ('Elevador de Carga Elétrico 200kg', 250.00, 6000.00, 2);