DELETE FROM bookstoredb.user;

INSERT INTO bookstoredb.user (email, password, full_name) VALUES ('cesare@gmail.com', 'AleaIactaEst', 'Cesare Augusto');
INSERT INTO bookstoredb.user (email, password, full_name) VALUES ('nerone@gmail.com', 'RomeOnFire', 'Nerone Germanico');
INSERT INTO bookstoredb.user (email, password, full_name) VALUES ('aurelio@gmail.com', 'Meditation', 'Marco Aurelio');

DELETE FROM bookstoredb.category;

INSERT INTO bookstoredb.category (name, flag_deleted) VALUES ('Children', 0);
INSERT INTO bookstoredb.category (name, flag_deleted) VALUES ('Self Improvement', 0);

DELETE FROM bookstoredb.book;

INSERT INTO bookstoredb.book (title, author, description, isbn, price, publish_date, category_id)
VALUES (
	'The Light We Carry: Overcoming in Uncertain Times',
	'Michelle Obama',
	'There may be no tidy solutions or pithy answers to life’s big challenges, but Michelle Obama believes that we can all locate and lean on a set of tools to help us better navigate change and remain steady within flux. In The Light We Carry, she opens a frank and honest dialogue with readers, considering the questions many of us wrestle with: How do we build enduring and honest relationships? How can we discover strength and community inside our differences? What tools do we use to address feelings of self-doubt or helplessness? What do we do when it all starts to feel like too much?
	 Michelle Obama offers readers a series of fresh stories and insightful reflections on change, challenge, and power, including her belief that when we light up for others, we can illuminate the richness and potential of the world around us, discovering deeper truths and new pathways for progress. Drawing from her experiences as a mother, daughter, spouse, friend, and First Lady, she shares the habits and principles she has developed to successfully adapt to change and overcome various obstacles—the earned wisdom that helps her continue to “become.” She details her most valuable practices, like “starting kind,” “going high,” and assembling a “kitchen table” of trusted friends and mentors. With trademark humor, candor, and compassion, she also explores issues connected to race, gender, and visibility, encouraging readers to work through fear, find strength in community, and live with boldness. 
	 “When we are able to recognize our own light, we become empowered to use it,” writes Michelle Obama. A rewarding blend of powerful stories and profound advice that will ignite conversation, The Light We Carry inspires readers to examine their own lives, identify their sources of gladness, and connect meaningfully in a turbulent world.',
	'0593237463',
	15.95,
	PARSEDATETIME('15/11/2022','dd/mm/yyyy'),
	(SELECT id FROM bookstoredb.category WHERE name = 'Self Improvement')
);

INSERT INTO bookstoredb.book (title, author, description, isbn, price, publish_date, category_id)
VALUES (
	'Atomic Habits',
	'James Clear',
	'No matter your goals, Atomic Habits offers a proven framework for improving--every day. James Clear, one of the world''s leading experts on habit formation, reveals practical strategies that will teach you exactly how to form good habits, break bad ones, and master the tiny behaviors that lead to remarkable results.
	 If you''re having trouble changing your habits, the problem isn''t you. The problem is your system. Bad habits repeat themselves again and again not because you don''t want to change, but because you have the wrong system for change. You do not rise to the level of your goals. You fall to the level of your systems. Here, you''ll get a proven system that can take you to new heights.
	 Clear is known for his ability to distill complex topics into simple behaviors that can be easily applied to daily life and work. Here, he draws on the most proven ideas from biology, psychology, and neuroscience to create an easy-to-understand guide for making good habits inevitable and bad habits impossible. Along the way, readers will be inspired and entertained with true stories from Olympic gold medalists, award-winning artists, business leaders, life-saving physicians, and star comedians who have used the science of small habits to master their craft and vault to the top of their field.
	 Learn how to:
	
	    - make time for new habits (even when life gets crazy);
	    - overcome a lack of motivation and willpower;
	    - design your environment to make success easier;
	    - get back on track when you fall off course;
	
	 ... and much more.
	 Atomic Habits will reshape the way you think about progress and success, and give you the tools and strategies you need to transform your habits--whether you are a team looking to win a championship, an organization hoping to redefine an industry, or simply an individual who wishes to quit smoking, lose weight, reduce stress, or achieve any other goal.
	',
	'0735211299',
	11.98,
	PARSEDATETIME('16/10/2018','dd/mm/yyyy'),
	(SELECT id FROM bookstoredb.category WHERE name = 'Self Improvement')
);

INSERT INTO bookstoredb.book (title, author, description, isbn, price, publish_date, category_id)
VALUES (
	'The Very Hungry Caterpillar',
	'Eric Carle',
	'"In the light of the moon a little egg lay on a leaf." So begins Eric Carle''s modern classic, The Very Hungry Caterpillar. More than 12 million copies of this book have been sold in its original, full-sized edition, and the beloved tale of science and gluttony has been translated into 20 languages. This five-by-four-inch miniature edition is truly tiny, with tiny type, but it is a nice size for small hands to hold and flip through the pictures. Despite its diminished state, the book is complete in every detail, following the ravenous caterpillar''s path as he eats his way through one apple (and the pages of the book itself) on Monday, two pears on Tuesday, three plums on Wednesday, and so on, through cherry pie and sausage--until he is really fat and has a stomachache. And no doubt you know what happens next! Kids love butterfly metamorphosis stories, and this popular favorite teaches counting and the days of the week, too. A fun gift package for caterpillar fans. (Baby to preschool) --Karin Snelson ',
	'0399226907',
	8.99,
	PARSEDATETIME('23/03/1994','dd/mm/yyyy'),
	(SELECT id FROM bookstoredb.category WHERE name = 'Children')
);