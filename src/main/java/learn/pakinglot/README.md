# Parking Lot

Package: `learn.pakinglot`

## Services

| Service | Status | Description |
|---------|--------|-------------|
| Issue Ticket | Implemented | Issues a parking ticket for a vehicle entering through a gate, assigning a parking slot. |
| Bill Generation | Planned | Generates a bill on exit, using a fees calculation strategy. |

## Package Structure

| Package | Purpose |
|---------|---------|
| `controllers` | Entry points, e.g. `TicketController` |
| `dtos` | Request/response objects, e.g. `IssueTicketRequestDTO`, `IssueTicketResponseDTO`, `ResponseDTO` |
| `services` | Business logic, e.g. `TicketService` / `TicketServiceImpl`, `VehicleService` |
| `models` | Domain entities and enums (`Ticket`, `Vehicle`, `ParkingLot`, `ParkingFloor`, `ParkingSlot`, `Gate`, `Operator`, `Bill`, `Payment`, ...) |
| `strategy` | Pluggable algorithms: `SlotAssignmentStrategy` (e.g. `RandomSlotAssignmentStrategy`) and `FeesCalculationStrategy` |
| `factory` | Creates strategies, e.g. `SlotAssignmentStrategyFactory` |
| `repositories` | Storage, currently `InMemoryRepository` |

Other files: `Clients.java` (driver/entry point) and `DataLoader.java` (seed data).

## Design Notes

- **Strategy pattern** for slot assignment (`SlotAssignmentStrategyType`) and fees calculation (`FeesCalculationStrategyType`), so new algorithms can be added without changing services.
- **Factory pattern** to pick the slot assignment strategy.
- **Controller → Service → Repository** layering with DTOs at the boundary.

## Flow: Issue Ticket

1. `TicketController` receives an `IssueTicketRequestDTO`.
2. `TicketService` resolves the gate, operator and vehicle.
3. A `SlotAssignmentStrategy` (from the factory) picks a free `ParkingSlot`.
4. A `Ticket` is created and an `IssueTicketResponseDTO` is returned.

## Upcoming: Bill Generation

- Look up the `Ticket` and compute the duration parked.
- Use a `FeesCalculationStrategy` to compute the amount.
- Create a `Bill` (`BillStatus`) and record the `Payment` (`PaymentMode`, `PaymentStatus`).
- Free the `ParkingSlot` on completion.

The models for this (`Bill`, `Payment`, and related enums) already exist; the service, controller and DTOs are still to be added.

---

[← Back to main README](../../../../../../README.md)
