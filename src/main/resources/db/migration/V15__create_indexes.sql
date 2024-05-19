CREATE INDEX establishment_owner_id_idx ON establishments (owner_id);
CREATE INDEX establihsment_category_idx ON establishments (category);

CREATE INDEX working_hours_establishment_id_idx ON working_hours (establishment_id);

CREATE INDEX photo_establishment_id_idx ON photo (establishment_id);

CREATE INDEX menu_category_establishment_id_idx ON menu_category (establishment_id);
CREATE INDEX menu_category_parent_category_id_idx ON menu_category (parent_category_id);

CREATE INDEX product_category_id_idx ON product (category_id);

CREATE INDEX review_establishment_id_idx ON review (establishment_id);

