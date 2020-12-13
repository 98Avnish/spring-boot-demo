Feature: Super smoothie loyalty card program

  Background:
    Given the following drink categories:
      | Drink      | Category | Points |
      | Banana     | Regular  | 15     |
      | Strawberry | Fancy    | 20     |
      | Tea        | Tea      | 10     |

  Scenario Outline: Earning points
    Given Micheal is a Customer
    When Micheal purchase <Quantity> <Drink> drink
    Then he should earn <Points> points

    Examples:
      | Drink      | Quantity | Points |
      | Banana     | 2        | 30     |
      | Strawberry | 1        | 20     |
      | Tea        | 3        | 30     |