CREATE TABLE recipe (
    id bigserial NOT NULL PRIMARY KEY,
    type character (30),
    name character(40),
    ingredients character (1000),
    instructions character (1000),
    duration character (30)

);

INSERT INTO recipe ( type, name, ingredients, instructions, duration)
VALUES ('Breakfast', 'Omleta in pita', '1 lingurita de ulei,2 linguri de ceapa tocata marunt, 1/4 ceasca de ardei rosu tocat marunt, 2 oua, sare, piper, 2 linguri branza feta maruntita, 2 linguri de humus' 'Se amesteca ingredientele (mai putin humusul) ca pentru omleta si se prajeste. Omleta se introduce in pita taiata partial pe jumatate', '30 minute');

DELETE FROM recipe
WHERE type = null;


