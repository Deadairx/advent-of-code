use std::str::FromStr;


#[derive(Debug)]
enum Command {
    Noop,
    Addx(i32)
}

impl FromStr for Command {
    type Err = String;

    fn from_str(s: &str) -> Result<Self, Self::Err> {
        let mut parts = s.split_whitespace();
        let cmd = parts.next().unwrap();
        match cmd {
            "noop" => Ok(Command::Noop),
            "addx" => Ok(Command::Addx(parts.next().unwrap().parse().unwrap())),
            _ => Err(format!("Unknown command: {}", cmd))
        }
    }
}

fn main() {
    // get string input from file
    let input = include_str!("./input1.txt");

    let mut cycle = 1;
    let mut signal_strength = 0;
    let mut x_value = 0;

    let output = input
        .lines()
        .map(|line| line.parse::<Command>().unwrap())
        .collect::<Vec<Command>>()
        .iter()
        .reduce(|command, signal_strength| {
            match command {
                Command::Noop => {
                    if at_cycle_checkpoint(&cycle) {
                        signal_strength += signal_strength + (x_value * cycle);
                    }
                },
                Command::Addx(x) => {
                    if at_cycle_checkpoint(&cycle) {
                        signal_strength += signal_strength + (x_value * cycle);
                    }
                    cycle += 1;
                    if at_cycle_checkpoint(&cycle) {
                        signal_strength += signal_strength + (x_value * cycle);
                    }
                    x_value += x;
                }
            }

            cycle += 1;
            return signal_strength;
        });

    println!("{:?}", output);
}

fn at_cycle_checkpoint(cycle: &i32) -> bool {
    *cycle == 20 || (*cycle - 20) % 40 == 0
}
