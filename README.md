# LearnLLD

A project for learning and practicing **Low Level Design (LLD)**. Each case study is a small, self-contained design exercise that lives in its own package under [src/main/java/learn](src/main/java/learn).

- **Language:** Java 21
- **Build:** Maven (`pom.xml`)

## Case Studies

Each case study has its own README next to its code, covering services, package structure and design notes.

| Case Study | Package | Status |
|------------|---------|--------|
| [Parking Lot](src/main/java/learn/pakinglot/README.md) | `learn.pakinglot` | In progress |
| BookMyShow | _planned_ | Not started |

> This README is only an index. Update it when a new case study is added; service-level details go in each case study's README.

---

## Adding a New Case Study

1. Create a new package under `learn` (e.g. `learn.bookmyshow`).
2. Follow the same layering: `controllers`, `dtos`, `services`, `models`, `repositories`, plus `strategy`/`factory` where useful.
3. Add a `README.md` inside the new package describing its services, structure and design notes.
4. Add a row to the **Case Studies** table above and link to that README.
