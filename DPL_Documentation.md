# 🦫 Declarative Physics Language 
###### An language to make physics calculations modular and reusable

---

MPL is a language working with a declarative principle.
It can be used to query modular physics based calculations.
Operations are handed over via sentences constructed out of keywords.
The Modular Physics Engine - MPE for short - is the program, that handles DPL-Inputs and splits them into the required modules and functions.



## Table of contents

---
 
 - [**Querks of DPL**](#querks-of-dpl)
 - [**Variables**](#variables)
 - [**Constants**](#constants)
 - [**Physics Modules**](#physics-modules)
 - [**Rounding Decimals**](#round-decimals)
 - [**Unit Conversion**](#unit-conversion)
 - [**Output and Execute**](#output-and-execute)
 - [**DPL Examples**](#dpl-examples)

## Querks of DPL

---

1. Keywords in DPL are not case-sensitive (`USE` is the same as `use`)
2. Variables and module names are case-sensitive (`Rounded_voltage` is not the same as `rounded_voltage`)
3. Measurement units are not saved internally
4. A DPL-Command is executed after an OUTPUT-event and an enter-press at the semicolon.

## Variables

---

Variables are a way to give numbers and outputs a descriptive name. In DPL they are saved into a hashmap.
That means they are saved as pairs of Name and Value.
Therefor internally there are no saved measurement units for variables - instead the Unit Converter gives the power of converting to the user.

Variables have to start with a letter or underscore. They can include numbers.

### Syntax

**Creating a variable:** `VAR <varName> = <value>`

**Assigning a variable to a variable:** `VAR <varName> = <varName>`

## Constants

---
Constants are constant and common values in physics like $\pi$ or $speed\_of\_light$. 
DPL initializes those values at the start of the program.
Just like variables constants are also saved into the variable-hashmap and can be used in the same ways as them.  

### Supported constants
 
 - $\pi$: `MATH_PI`
 - $e$: `MATH_E`
 - $universal\_gas\_constant$: `UNIV_GAS_CONST` ($8.314 \ J \cdot K^{-1} \cdot mol^{-1}$)
 - $standard\_gravity$: `STANDARD_GRAVITY` ($9.80665 \ m/s^2$)
 - $speed\_of\_light$: `SPEED_OF_LIGHT` ($299792458.0 \ m/s$)

Note that in contrast to keywords, constants are case-sensitive and always written in capital letters.

## Physics Modules

---

### Example

**Ohm's Law:** $V = I \cdot R$ 

If we were to input the current $I$ and resistance $R$ we would get the voltage $V$.
This formula accepts `current`, `voltage` and `resistance`.

A Physics Module (PM) is essentially a formula which accepts inputs of units and outputs the missing one as in the example above.
They can also be chained together which allows for complex physical calculations.

For the example above the Syntax would look something like this: `USE OhmsLaw WITH current = 55, resistance = 30 OUT voltage_out` 
Here the value of the output voltage is stored into the variable `voltage_out`.

### Syntax

**Using a Physics Module:** 
```
USE <moduleName> WITH <input1> = <value1>, <input2> = <value2>, ... OUT <output>
```

**Chaining of Physics Modules:** 

```
USE <moduleName> WITH <input1> = <value1>, <input2> = <value2>, ... OUT <output>
USE <moduleName> WITH <input1> = <output>, <input2> = <value3>, ... OUt <output2>
```

### Supported PM-inputs

| Keyword               | Var       | Unit              |
| --------------------- | --------- | ----------------- |
| `current`             | $I$       | $Ampere$          |
| `voltage`             | $V$       | $Volt$            |
| `resistance`          | $R$       | $Ohm$             |
| `power`               | $P$       | $Watt$            |
| `charge`              | $Q$       | $Coulomb$         |
| `capacitance`         | $C$       | $Farad$           |
| `inductance`          | $L$       | $Henry$           |
| `frequency`           | $f$       | $Hertz$           |
| `mass`                | $m$       | $kg$              |
| `length`              | $s$       | $m$               |
| `area`                | $A$       | $m^2$             |
| `time`                | $t$       | $s$               |
| `velocity`            | $v$       | $m/s$             |
| `acceleration`        | $a$       | $m/s^2$           |
| `force`               | $F$       | $Newton$          |
| `energy`              | $E$       | $Joule$           |
| `pressure`            | $p$       | $Pascal$          |
| `moment`              | $M$       | $N \cdot m$       |
| `temperature`         | $T$       | $Kelvin$          |
| `heat`                | $Q$       | $Joule$           |
| `heat_capacity`       | $c$       | $J/kg \cdot K$    |
| `entropy`             | $S$       | $J/K$             |
| `density`             | $\rho$    | $kg/m^3$          |
| `volume`              | $V$       | $m^3$             |
| `conductance`         | $G$       | $Siemens, \\ 1/R$ |
| `magnetic_field`      | $B$       | $Tesla$           |
| `magnetic_flux`       | $\phi$    | $Weber$           |
| `amoung_of_substance` | $n$       | $Mol$             |
| `gas_constant`        | $R$       | $J/mol \cdot K$   |
| `wavelength`          | $\lambda$ | $m$               |
| `speed_of_light`      | $c$       | $m/s$             |
| `spring_constant`     | $...$     | $...$             |
| `normal_gravity`      | $...$     | $...$             |

### Supported PMs

| Law name             | Module name      | Formula                                                                               |
|----------------------|------------------| ------------------------------------------------------------------------------------- |
| Ohm's Law            | `OhmsLaw`        | $current = voltage / resistance$                                                      |
| Power Law            | `PowerLaw`       | $power = voltage \cdot current$                                                       |
| Extended Power Law   | `PowerLawExt`    | $power = current \cdot current \cdot resistance$                                      |
| Capacitance          | `CapacitanceLaw` | $charge = capacitance \cdot voltage$                                                  |
| Newton's Second Law  | `NewtonTwo`      | $force = mass \cdot acceleration$                                                     |
| Kinetic Energy       | `KineticEnergy`  | $energy = 1/2 \cdot mass \cdot velocity \cdot velocity$                               |
| Work                 | `WorkLaw`        | $energy = force \cdot length$                                                         |
| Momentum             | `MomentumLaw`    | $impulse = mass \cdot velocity$                                                       |
| Pressure             | `PressureLaw`    | $pressure = force / area$                                                             |
| Density              | `DensityLaw`     | $density = mass / volume$                                                             |
| Hooke's Law          | `HookesLaw`      | $force = spring\_constant \cdot length$                                               |
| Specific Heat        | `SpecificHeat`   | $heat = mass \cdot heat\_capacity \cdot temperature$                                  |
| Ideal Gas Law        | `IdealGasLaw`    | $pressure \cdot volume = amount\_of\_substance \cdot gas\_constant \cdot temperature$ |
| Frequency-Wavelength | `FreqWaveRel`    | $wavelength = speed\_of\_light/frequency$                                             |

Note that if you want to input units you use the description of the Formula. E.g. to use `OhmsLaw` inputs of `current`, `voltage` or `resistance` are possible.


## Round Decimals

---

In physics outputs ofter have a lot of decimals. With the PM `Round` you can reduce them to $n$ decimals after the comma.
The `Round` Module accepts two values:

1. `value`: this is the value or variable you want to reduce
2. `decimals`: this accepts an integer as the number of decimals

**Example:**
`USE Round WITH value = 3.141592, decimals = 2 OUT pi_rounded`
This will save $3.14$ into `pi_rounded`.

### Syntax
**Using Round:**

```
USE Round WITH value = <value1>, decimals = <value2> OUT <output>
```

## Unit Conversion

Since there are no internally saved units of measurement in DPL the user has to handel this manner for himself.
To help there is a build in Unit Converter which changes one value or variable into another unit.

**Example:** `CONVERT 5000[meter] INTO [kilometer] OUT kilometer_out` 
This converts $5000m$ into $5km$. `kilometer_out` has now the value $5$.

### Syntax

```
CONVERT <input>[<startUnit>] INTO [<endUnit>] OUT <output>
```

### Supported Measurement Units

A measurement unit combines a prefix and suffix. 

**Supported SI-prefixes:**
```
quetta - Q
ronna - R
yotta - Y
zetta - Z
exa - E
peta - P
tera - T
giga - G
mega - M
kilo - k
hecto - h
deca - da

deci - d
centi - c
milli - m
micro - mic
nana - n
pico - p
femto - f
atto - a
zepto - z
yocto - y
ronto - r
quecto - qu
```

**Supported SI-suffixes:**
```
meters - m
grams - g
ampere
mole
candela
```

If you don't have a specific suffix for conversion you can use an underscore like:
`CONVERT 5000[_] INTO [kilo_] OUT kilo_out`

Note that you can also use short-forms like `km` instead of `kilometer`. 


## Output and Execute

---

Before a DPL-command executes, you have to describe which outputs you want displayed. 
For this matter we use OUTPUT-events which take up to an unlimited amount of comma-separated variables as inputs.

There are different ways to output in DPL. The most common is the use of the `Display`.
This will print all input variables in pairs of `name` and `value` inside the console.

### Syntax

**General output:**
```
THEN OUTPUT <variable1>, <variable2>, ... VIA <displayMethod>;
```

**Output via Diplay:**
```
THEN OUTPUT <variable1>, <variable2>, ... VIA Display;
```

Note that there is a semicolon at the end of an output-event. 
Written DPL-code only executes after this semicolon is written and enter is pressed.

## DPL Examples














