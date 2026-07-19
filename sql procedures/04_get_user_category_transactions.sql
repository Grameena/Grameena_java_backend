-- Detailed category-wise transactions for one user.
CREATE OR REPLACE FUNCTION public.get_user_category_transactions(
    p_user_id bigint,
    p_category_id integer
)
RETURNS TABLE (
    transaction_id integer,
    transactional_crop_id integer,
    field_id integer,
    field_name varchar,
    crop_id integer,
    crop_name varchar,
    category_id integer,
    category_name varchar,
    unit_id integer,
    unit_name varchar,
    is_expense boolean,
    quantity numeric,
    price numeric,
    total_amount numeric,
    transaction_date timestamptz,
    notes varchar
)
LANGUAGE sql
AS $$
    SELECT
        t.transaction_id,
        tc.transactional_crop_id,
        f.field_id,
        f.field_name,
        c.crop_id,
        c.crop_name,
        cat.transaction_category_id AS category_id,
        cat.transaction_category_name AS category_name,
        u.unit_id,
        u.unit_name,
        t.is_expense,
        t.quantity,
        t.price,
        t.total_amount,
        t.transaction_date,
        t.notes
    FROM public.transactions t
    INNER JOIN public.transactional_crops tc
        ON t.transactional_crop_id = tc.transactional_crop_id
    INNER JOIN public.fields f
        ON tc.field_id = f.field_id
    INNER JOIN public.crops c
        ON tc.crop_id = c.crop_id
    INNER JOIN public.transaction_categories cat
        ON t.category_id = cat.transaction_category_id
    INNER JOIN public.units u
        ON t.unit_id = u.unit_id
    WHERE f.user_id = p_user_id
      AND t.category_id = p_category_id
    ORDER BY t.transaction_date DESC, t.transaction_id DESC;
$$;
