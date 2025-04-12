INSERT INTO requirement (creationDate, modificationDate, priority, version, description, title, requirementStatus)
VALUES
    ('2024-03-14', '2024-03-14', '1', 1.0, 'Als Benutzer möchte ich mich mit meiner E-Mail und Passwort anmelden können, um auf meine persönlichen Daten zuzugreifen.', 'Login-Funktionalität', 'ToDO'),
    ('2024-03-14', '2024-03-14', '2', 1.1, 'Als Administrator möchte ich Benutzerrollen verwalten können, um unterschiedliche Zugriffsrechte zu gewähren.', 'Benutzerrollen-Verwaltung', 'IN_PROGRESS'),
    ('2024-03-14', '2024-03-14', '3', 2.0, 'Als Benutzer möchte ich meine Profildaten bearbeiten können, um meine persönlichen Informationen aktuell zu halten.', 'Profil-Bearbeitung', 'REJECTED'),
    ('2024-03-14', '2024-03-14', '1', 1.0, 'Als Kunde möchte ich Produkte nach Kategorien filtern können, um schneller das passende Produkt zu finden.', 'Produktfilter', 'TODO'),
    ('2024-03-14', '2024-03-14', '2', 1.1, 'Als Benutzer möchte ich Benachrichtigungen über wichtige Updates erhalten, um stets informiert zu bleiben.', 'Benachrichtigungssystem', 'IN_PROGRESS');

INSERT INTO TestCase (description, requirement_id)
VALUES
    ('Überprüfe, ob sich ein Benutzer mit gültigen Anmeldeinformationen erfolgreich anmelden kann.', 1),
    ('Stelle sicher, dass ein Administrator neue Benutzerrollen erstellen und bearbeiten kann.', 2),
    ('Prüfe, ob ein Benutzer sein Profil erfolgreich aktualisieren kann.', 3),
    ('Teste, ob die Produktfilterung nach Kategorien korrekt funktioniert.', 4),
    ('Validiere, dass Benachrichtigungen für wichtige Updates korrekt gesendet werden.', 5);

INSERT INTO TestRun (runNumber, tester_userId)
VALUES
    (1, 4),
    (2, 5),
    (3, 6),
    (4, 7),
    (5, 8);

Insert INTO TestCaseTestRun (passed,testfall, testrun)
VALUES
    (false,1,1),
    (false,2,2),
    (false,3,3),
    (false,4,4),
    (false,5,5);