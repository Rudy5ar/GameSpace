-- GameSpace database: schema + some sample data
-- Users: admin / admin123 (admin), demo / demo123 (user)

CREATE DATABASE IF NOT EXISTS gamespace;
USE gamespace;

CREATE TABLE role (
  idrole INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(45) NOT NULL
);

CREATE TABLE users (
  user_id INT AUTO_INCREMENT PRIMARY KEY,
  email VARCHAR(100),
  password VARCHAR(100) NOT NULL,
  username VARCHAR(45) NOT NULL UNIQUE,
  idRole INT,
  FOREIGN KEY (idRole) REFERENCES role(idrole)
);

CREATE TABLE games (
  game_id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(100) NOT NULL,
  genre VARCHAR(45),
  platform VARCHAR(100)
);

CREATE TABLE studios (
  studio_id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL
);

CREATE TABLE games_studios (
  game_id INT NOT NULL,
  studio_id INT NOT NULL,
  PRIMARY KEY (game_id, studio_id),
  FOREIGN KEY (game_id) REFERENCES games(game_id) ON DELETE CASCADE,
  FOREIGN KEY (studio_id) REFERENCES studios(studio_id) ON DELETE CASCADE
);

CREATE TABLE genres (
  genre_id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(45) NOT NULL
);

CREATE TABLE game_genres (
  genre_id INT NOT NULL,
  game_id INT NOT NULL,
  PRIMARY KEY (genre_id, game_id),
  FOREIGN KEY (genre_id) REFERENCES genres(genre_id) ON DELETE CASCADE,
  FOREIGN KEY (game_id) REFERENCES games(game_id) ON DELETE CASCADE
);

CREATE TABLE comments (
  comment_id INT AUTO_INCREMENT PRIMARY KEY,
  comment_text TEXT,
  game_id INT,
  user_id INT NOT NULL,
  FOREIGN KEY (game_id) REFERENCES games(game_id) ON DELETE CASCADE,
  FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE user_games (
  user_game_id INT AUTO_INCREMENT PRIMARY KEY,
  game_id INT,
  user_id INT,
  FOREIGN KEY (game_id) REFERENCES games(game_id) ON DELETE CASCADE,
  FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE reviews (
  review_id INT AUTO_INCREMENT PRIMARY KEY,
  rating INT,
  review_date DATETIME,
  review_text TEXT,
  game_id INT,
  user_id INT,
  FOREIGN KEY (game_id) REFERENCES games(game_id) ON DELETE CASCADE,
  FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- new users are registered with role id 1
INSERT INTO role (idrole, name) VALUES (1, 'user'), (2, 'admin');

INSERT INTO users (email, password, username, idRole) VALUES
  ('admin@gamespace.com', '$2a$10$qj01WiiX2oXR3.U0O7AP7OvRDubFCN9YdsH5O0aaR7S/onENK.CGm', 'admin', 2),
  ('demo@gamespace.com', '$2a$10$EhO.NnOn48MjC1atukhCvOhgB/gZqwm8SGe1po1B3QFBHNuf6S.BC', 'demo', 1);

INSERT INTO games (title, genre, platform) VALUES
  ('The Witcher 3: Wild Hunt', 'RPG', 'PC, PS4, Xbox One, Switch'),
  ('Elden Ring', 'Action RPG', 'PC, PS5, Xbox Series X'),
  ('Hollow Knight', 'Metroidvania', 'PC, Switch'),
  ('Red Dead Redemption 2', 'Action-adventure', 'PC, PS4, Xbox One'),
  ('Stardew Valley', 'Simulation', 'PC, Switch, Mobile'),
  ('Baldur''s Gate 3', 'RPG', 'PC, PS5'),
  ('Counter-Strike 2', 'FPS', 'PC');

INSERT INTO studios (name) VALUES
  ('CD Projekt Red'), ('FromSoftware'), ('Team Cherry'), ('Rockstar Games'),
  ('ConcernedApe'), ('Larian Studios'), ('Valve'), ('Bandai Namco');

INSERT INTO games_studios (game_id, studio_id) VALUES
  (1, 1), (2, 2), (2, 8), (3, 3), (4, 4), (5, 5), (6, 6), (7, 7);

INSERT INTO comments (comment_text, game_id, user_id) VALUES
  ('Najbolji RPG ikada, Blood and Wine je savrsen.', 1, 2),
  ('Margit me ubio 30 puta.', 2, 2),
  ('Odlicna igra, preporuka!', 2, 1);

INSERT INTO user_games (game_id, user_id) VALUES (1, 2), (3, 2), (6, 2);
