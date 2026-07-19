-- Creates a field if not present for the user with same name+season, else returns existing field_id.
CREATE OR REPLACE FUNCTION public.create_or_get_field(
    p_user_id bigint,
    p_field_name varchar,
    p_acres numeric,
    p_soil_type integer,
    p_irrigation_type integer,
    p_season varchar
)
RETURNS integer
LANGUAGE plpgsql
AS $$
DECLARE
    v_field_id integer;
BEGIN
    SELECT field_id
    INTO v_field_id
    FROM public.fields
    WHERE user_id = p_user_id
      AND lower(field_name) = lower(p_field_name)
      AND lower(season) = lower(p_season)
    LIMIT 1;

    IF v_field_id IS NOT NULL THEN
        RETURN v_field_id;
    END IF;

    INSERT INTO public.fields (
        user_id,
        field_name,
        acres,
        soil_type,
        irrigation_type,
        season
    ) VALUES (
        p_user_id,
        p_field_name,
        p_acres,
        p_soil_type,
        p_irrigation_type,
        p_season
    )
    RETURNING field_id INTO v_field_id;

    RETURN v_field_id;
END;
$$;
