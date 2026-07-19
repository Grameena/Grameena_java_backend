-- Creates and returns transactional crop id for selected field and crop.
CREATE OR REPLACE FUNCTION public.create_transactional_crop(
    p_field_id integer,
    p_crop_id integer,
    p_crop_acres numeric,
    p_planting_date date DEFAULT NULL
)
RETURNS integer
LANGUAGE plpgsql
AS $$
DECLARE
    v_transactional_crop_id integer;
BEGIN
    INSERT INTO public.transactional_crops (
        crop_id,
        field_id,
        crop_acres,
        planting_date
    ) VALUES (
        p_crop_id,
        p_field_id,
        p_crop_acres,
        p_planting_date
    )
    RETURNING transactional_crop_id INTO v_transactional_crop_id;

    RETURN v_transactional_crop_id;
END;
$$;
