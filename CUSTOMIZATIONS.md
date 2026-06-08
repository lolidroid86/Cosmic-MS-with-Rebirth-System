# Cosmic-MS — Custom Modifications

This is a fork of [Cosmic](https://github.com/P0nk/Cosmic) (HeavenMS v83) with the following custom features added.

---

## Rebirth System

Players who reach level 200 can rebirth, resetting to level 1 while gaining permanent bonus stats each time.

### Command

```
@rebirth
```

Requires level 200 (or the class max if using a multi-job class).  
Available to all players (GM level 0).

### Effects per rebirth

| Bonus | Amount |
|---|---|
| AP gained | 500 |
| Max HP bonus | +1000 × reborn count (cumulative) |
| Max MP bonus | +500 × reborn count (cumulative) |
| World announcement | Yes — `"<name> has been reborn! [Rebirth #N]"` |

Stats are permanent and persist across rebirths.

### Database

The `characters` table includes a `reborns INT NOT NULL DEFAULT '0'` column (already in `002-character.sql`). A fresh database setup picks this up automatically.

For an existing database, run:
```sql
ALTER TABLE characters ADD COLUMN reborns INT NOT NULL DEFAULT '0';
```

---

## Expanded GM Buffs

`BuffMeCommand` (`@buffme`, GM level 1) applies a broader set of buffs including all resistance types and extended durations. Source: `src/main/java/client/command/commands/gm1/BuffMeCommand.java`.

---

## Portable MySQL (`MySQL.bat`)

A batch script to run a portable MySQL 8.4.2 installation from a `mysql/` subfolder in the repo root. The `mysql/` folder itself is excluded from git (621 MB of binaries).

**To use it:**
1. Download [MySQL Community Server 8.4.x](https://dev.mysql.com/downloads/mysql/) — choose the ZIP archive (no installer).
2. Extract to `mysql/` in the repo root (i.e., `Cosmic-MS\mysql\bin\mysqld.exe` should exist).
3. Initialise the data directory once: `mysql\bin\mysqld --initialize-insecure --datadir=mysql\data`
4. Run `MySQL.bat` to start the server.

The server binds to the default port 3306 with no root password (`--initialize-insecure`). Change this before exposing to a network.

---

## Other Fixes

The commit history includes additional fixes to:

- `ItemFactory.java` / `ItemInformationProvider.java` — item handling edge cases
- `AbstractDealDamageHandler.java` / `SpecialMoveHandler.java` — damage calculation adjustments
- `CashOperationHandler.java` / `ItemPickupHandler.java` — inventory edge cases
- `LoginPasswordHandler.java` — login flow
- `MapleMap.java` — map logic
- `PacketCreator.java` — packet structure

See `git log --oneline` for the full change list.

---

## Divergence from Upstream

This fork targets commit `951e0331c`. Merging upstream Cosmic changes may require resolving conflicts in `Character.java`, `CommandsExecutor.java`, and `CharacterFactory.java`.
