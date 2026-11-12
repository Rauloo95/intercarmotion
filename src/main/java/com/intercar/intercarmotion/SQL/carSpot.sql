DO $$
    DECLARE
        -- 1. Marcas (Nombre, País)
        v_brands TEXT[][] := ARRAY[
            ['Porsche', 'Alemania'],
            ['BMW', 'Alemania'],
            ['Audi', 'Alemania'],
            ['Mercedes-AMG', 'Alemania'],
            ['Volkswagen', 'Alemania'],
            ['Cupra', 'España'],
            ['SEAT', 'España'],
            ['Ferrari', 'Italia'],
            ['Lamborghini', 'Italia'],
            ['Alfa Romeo', 'Italia'],
            ['Toyota', 'Japón'],
            ['Honda', 'Japón'],
            ['Nissan', 'Japón'],
            ['Mazda', 'Japón'],
            ['Ford', 'Estados Unidos'],
            ['Chevrolet', 'Estados Unidos'],
            ['Tesla', 'Estados Unidos'],
            ['Hyundai', 'Corea del Sur'],
            ['Kia', 'Corea del Sur'],
            ['Aston Martin', 'Reino Unido']
            ];

        -- 2. Modelos base (Índice marca 1-based, Nombre, Segmento, Año inicio, Año fin, URL Imagen)
        v_models TEXT[][] := ARRAY[
            ['1', '911 GT3 RS', 'Deportivo', '2022', '2025', 'https://images.unsplash.com/photo-1503376780353-7e6692767b70?auto=format&fit=crop&w=1000&q=80'],
            ['1', 'Taycan Turbo S', 'Sedán Eléctrico', '2020', '2025', 'https://images.unsplash.com/photo-1614162692292-7ac56d7f7f1e?auto=format&fit=crop&w=1000&q=80'],
            ['2', 'M4 Competition', 'Coupé', '2021', '2025', 'https://images.unsplash.com/photo-1617531653332-bd46c24f2068?auto=format&fit=crop&w=1000&q=80'],
            ['2', 'M3 Touring', 'Familiar', '2022', '2025', 'https://images.unsplash.com/photo-1555215695-3004980ad54e?auto=format&fit=crop&w=1000&q=80'],
            ['3', 'RS6 Avant', 'Familiar', '2020', '2025', 'https://images.unsplash.com/photo-1606664515524-ed2f786a0bd6?auto=format&fit=crop&w=1000&q=80'],
            ['3', 'R8 V10 Performance', 'Superdeportivo', '2019', '2024', 'https://images.unsplash.com/photo-1544636331-e26879cd4d9b?auto=format&fit=crop&w=1000&q=80'],
            ['4', 'AMG GT Black Series', 'Deportivo', '2020', '2023', 'https://images.unsplash.com/photo-1618843479313-40f8afb4b4d8?auto=format&fit=crop&w=1000&q=80'],
            ['4', 'A45 S AMG', 'Compacto', '2020', '2025', 'https://images.unsplash.com/photo-1605559424843-9e4c228bf1c2?auto=format&fit=crop&w=1000&q=80'],
            ['5', 'Golf R', 'Compacto', '2021', '2025', 'https://images.unsplash.com/photo-1541899481282-d53bffe3c35d?auto=format&fit=crop&w=1000&q=80'],
            ['6', 'Formentor VZ5', 'Crossover', '2021', '2025', 'https://images.unsplash.com/photo-1542282088-72c9c27ed0cd?auto=format&fit=crop&w=1000&q=80'],
            ['7', 'León FR', 'Compacto', '2020', '2025', 'https://images.unsplash.com/photo-1549317661-bd32c8ce0db2?auto=format&fit=crop&w=1000&q=80'],
            ['8', 'F8 Tributo', 'Superdeportivo', '2019', '2023', 'https://images.unsplash.com/photo-1583121274602-3e2820c69888?auto=format&fit=crop&w=1000&q=80'],
            ['8', 'SF90 Stradale', 'Híbrido Deportivo', '2020', '2025', 'https://images.unsplash.com/photo-1592198084033-aade902d1aae?auto=format&fit=crop&w=1000&q=80'],
            ['9', 'Huracán STO', 'Superdeportivo', '2021', '2024', 'https://images.unsplash.com/photo-1544829099-b9a0c07fad1a?auto=format&fit=crop&w=1000&q=80'],
            ['9', 'Urus Performante', 'SUV Deportivo', '2022', '2025', 'https://images.unsplash.com/photo-1621135802920-133df287f89c?auto=format&fit=crop&w=1000&q=80'],
            ['10', 'Giulia Quadrifoglio', 'Sedán', '2018', '2025', 'https://images.unsplash.com/photo-1616422285623-13ff0162193c?auto=format&fit=crop&w=1000&q=80'],
            ['11', 'GR Supra', 'Coupé', '2019', '2025', 'https://images.unsplash.com/photo-1607603750909-408e193868c7?auto=format&fit=crop&w=1000&q=80'],
            ['11', 'GR Yaris', 'Compacto', '2020', '2025', 'https://images.unsplash.com/photo-1626668893632-6f3a4466d22f?auto=format&fit=crop&w=1000&q=80'],
            ['12', 'Civic Type R', 'Compacto', '2022', '2025', 'https://images.unsplash.com/photo-1609521263047-f8f205293f24?auto=format&fit=crop&w=1000&q=80'],
            ['13', 'GT-R Nismo', 'Deportivo', '2017', '2024', 'https://images.unsplash.com/photo-1580273916550-e323be2ae537?auto=format&fit=crop&w=1000&q=80'],
            ['14', 'MX-5 Miata RF', 'Roadster', '2019', '2025', 'https://images.unsplash.com/photo-1552519507-da3b142c6e3d?auto=format&fit=crop&w=1000&q=80'],
            ['15', 'Mustang Dark Horse', 'Muscle Car', '2023', '2025', 'https://images.unsplash.com/photo-1584345604476-8ec5e12e42dd?auto=format&fit=crop&w=1000&q=80'],
            ['16', 'Corvette Z06', 'Deportivo', '2022', '2025', 'https://images.unsplash.com/photo-1552519507-88aa2dfa9fdb?auto=format&fit=crop&w=1000&q=80'],
            ['17', 'Model S Plaid', 'Sedán Eléctrico', '2021', '2025', 'https://images.unsplash.com/photo-1560958089-b8a1929cea89?auto=format&fit=crop&w=1000&q=80'],
            ['17', 'Model 3 Performance', 'Sedán Eléctrico', '2020', '2025', 'https://images.unsplash.com/photo-1536700503339-1e4b06520771?auto=format&fit=crop&w=1000&q=80'],
            ['18', 'Ioniq 5 N', 'Compacto Eléctrico', '2023', '2025', 'https://images.unsplash.com/photo-1621007947382-bb3c3994e3fb?auto=format&fit=crop&w=1000&q=80'],
            ['18', 'i30 N Performance', 'Compacto', '2021', '2024', 'https://images.unsplash.com/photo-1549399542-7e3f8b79c341?auto=format&fit=crop&w=1000&q=80'],
            ['19', 'EV6 GT', 'Crossover Eléctrico', '2022', '2025', 'https://images.unsplash.com/photo-1619767886558-efdc259cde1a?auto=format&fit=crop&w=1000&q=80'],
            ['20', 'Vantage F1 Edition', 'Gran Turismo', '2021', '2024', 'https://images.unsplash.com/photo-1600712242805-5f78671b24da?auto=format&fit=crop&w=1000&q=80'],
            ['20', 'DB12 Volante', 'Gran Turismo', '2023', '2025', 'https://images.unsplash.com/photo-1603584173870-7f23fdae1b7a?auto=format&fit=crop&w=1000&q=80']
            ];

        -- 3. Acabados / Ediciones para multiplicar variantes (10 variantes x 30 modelos = 300 coches)
        v_trims TEXT[] := ARRAY[
            'Base', 'Sport', 'Track Pack', 'Carbon Edition', 'Black Line',
            'Performance', 'Launch Edition', 'Competition', 'GT Line', 'Ultimate'
            ];

        v_brand_ids BIGINT[];
        v_new_brand_id BIGINT;
        v_new_car_id BIGINT;
        v_model_idx INT;
        v_trim_idx INT;
        v_b_idx INT;
        v_fuel TEXT;
        v_hp INT;
        v_cc INT;
        v_torque INT;
        v_accel NUMERIC(3,1);
        i INT;
    BEGIN
        -- Insertar marcas evitando duplicados si ya ejecutaste algo antes
        FOR i IN 1..array_length(v_brands, 1) LOOP
                INSERT INTO brands (name, country, logo_url)
                VALUES (v_brands[i][1], v_brands[i][2], NULL)
                ON CONFLICT (name) DO UPDATE SET country = EXCLUDED.country
                RETURNING id INTO v_new_brand_id;

                v_brand_ids := array_append(v_brand_ids, v_new_brand_id);
            END LOOP;

        -- Generar 300 coches combinando los 30 modelos con los 10 acabados
        FOR i IN 1..300 LOOP
                v_model_idx := ((i - 1) % array_length(v_models, 1)) + 1;
                v_trim_idx  := ((i - 1) / array_length(v_models, 1)) + 1;
                v_b_idx     := v_models[v_model_idx][1]::INT;

                -- Insertar el coche
                INSERT INTO car_models (name, segment, year_from, year_to, brand_id)
                VALUES (
                           v_models[v_model_idx][2] || ' ' || v_trims[v_trim_idx],
                           v_models[v_model_idx][3],
                           v_models[v_model_idx][4]::INT,
                           v_models[v_model_idx][5]::INT,
                           v_brand_ids[v_b_idx]
                       )
                RETURNING id INTO v_new_car_id;

                -- Calcular specs coherentes según si es eléctrico, híbrido o combustión
                IF v_models[v_model_idx][3] LIKE '%Eléctrico%' THEN
                    v_fuel   := 'ELECTRIC';
                    v_hp     := 450 + (v_trim_idx * 45);
                    v_cc     := NULL;
                    v_torque := 650 + (v_trim_idx * 35);
                    v_accel  := GREATEST(2.1, ROUND((3.8 - (v_trim_idx * 0.12))::NUMERIC, 1));
                ELSIF v_models[v_model_idx][3] LIKE '%Híbrido%' THEN
                    v_fuel   := 'HYBRID';
                    v_hp     := 600 + (v_trim_idx * 35);
                    v_cc     := 3990;
                    v_torque := 750 + (v_trim_idx * 25);
                    v_accel  := GREATEST(2.5, ROUND((3.2 - (v_trim_idx * 0.06))::NUMERIC, 1));
                ELSE
                    v_fuel   := CASE WHEN (i % 7 = 0) THEN 'DIESEL' ELSE 'GASOLINE' END;
                    v_hp     := 220 + (v_trim_idx * 38) + ((v_model_idx % 5) * 30);
                    v_cc     := 1984 + ((v_model_idx % 4) * 500);
                    v_torque := 320 + (v_trim_idx * 32);
                    v_accel  := GREATEST(2.8, ROUND((6.2 - (v_trim_idx * 0.25))::NUMERIC, 1));
                END IF;

                -- Insertar motor principal
                INSERT INTO engines (name, fuel_type, horsepower, displacement_cc, torque_nm, transmission, acceleration0100, car_model_id)
                VALUES (
                           CASE
                               WHEN v_fuel = 'ELECTRIC' THEN 'Dual Motor AWD (' || v_hp || ' CV)'
                               WHEN v_fuel = 'HYBRID'   THEN 'V8 Biturbo Plug-In Hybrid'
                               WHEN v_fuel = 'DIESEL'   THEN ROUND(v_cc / 1000.0, 1) || ' TDI Biturbo'
                               ELSE ROUND(v_cc / 1000.0, 1) || ' Twin-Scroll Turbo'
                               END,
                           v_fuel,
                           v_hp,
                           v_cc,
                           v_torque,
                           CASE WHEN v_fuel = 'ELECTRIC' THEN 'Automática 1 velocidad' ELSE 'Automática Doble Embrague 8v' END,
                           v_accel,
                           v_new_car_id
                       );

                -- Para los modelos pares, añadir una segunda motorización alternativa
                IF i % 2 = 0 AND v_fuel = 'GASOLINE' THEN
                    INSERT INTO engines (name, fuel_type, horsepower, displacement_cc, torque_nm, transmission, acceleration0100, car_model_id)
                    VALUES (
                               '2.0 Turbo Access',
                               'GASOLINE',
                               GREATEST(150, v_hp - 90),
                               1998,
                               GREATEST(250, v_torque - 100),
                               'Manual 6 velocidades',
                               LEAST(8.5, v_accel + 1.4),
                               v_new_car_id
                           );
                END IF;

                -- Insertar imagen principal y una secundaria
                INSERT INTO car_images (url, caption, is_primary, car_model_id)
                VALUES
                    (v_models[v_model_idx][6], v_models[v_model_idx][2] || ' Vista Exterior', true, v_new_car_id),
                    ('https://images.unsplash.com/photo-1492144534655-ae79c964c9d7?auto=format&fit=crop&w=1000&q=80', 'Vista Detalle', false, v_new_car_id);

            END LOOP;
    END $$;