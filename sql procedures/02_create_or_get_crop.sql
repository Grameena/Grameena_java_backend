-- Creates a crop if not present (case-insensitive name), else returns existing crop_id.
CREATE OR REPLACE FUNCTION public.create_or_get_crop(
    p_crop_name varchar,
    p_crop_image_id integer DEFAULT NULL
)
RETURNS integer
LANGUAGE plpgsql
AS $$
DECLARE
    v_crop_id integer;
BEGIN
    SELECT crop_id
    INTO v_crop_id
    FROM public.crops
    WHERE lower(crop_name) = lower(p_crop_name)
    LIMIT 1;

    IF v_crop_id IS NOT NULL THEN
        RETURN v_crop_id;
    END IF;

    INSERT INTO public.crops (crop_name, crop_image_id)
    VALUES (p_crop_name, p_crop_image_id)
    RETURNING crop_id INTO v_crop_id;

    RETURN v_crop_id;
END;
$$;
