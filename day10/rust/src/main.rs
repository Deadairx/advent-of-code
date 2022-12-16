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
        let arg = parts.next().unwrap();
        match cmd {
            "noop" => Ok(Command::Noop),
            "addx" => Ok(Command::Addx(arg.parse().unwrap())),
            _ => Err(format!("Unknown command: {}", cmd))
        }
    }
}

fn main() {
    // get string input from file
    let input = include_str!("./input1.txt");

    let output = input
        .lines()
        .map(|line| line.parse::<Command>().unwrap());

    println!("{:?}", output);
}
