# rn-chart-desk 📊

A powerful & easy to use chart library for creating customizable charts in React Native (Please note that this package does not have iOS support yet).


**Table of content:**
 - [Installation](#installation)
 - [Line Chart](#linechart)
 - [Pie Chart](#piechart)
 - [Bar Chart](#barchart)
 - [Grouped Bar Chart](#grouped-barchart)
 - [Author](#author)

## TODO: iOS Version

⚠️ When passing data to any of the charts, make sure you pass as `JSON.stringify()`

<a id="installation"></a>
## Installation

```sh
npm install rn-chart-desk
```

<a id="linechart"></a>
## LineChart

![rn-chart-desk React Native Line Chart](assets/linechart.png "rn-chart-desk LineChart")

```js
import { LineChart } from "rn-chart-desk";

// ...

<LineChart
	style={{ flex: 1 }}
	data={JSON.stringify({
		values: [34, 2, 15, 29, 5],
		label: "Second Test",
		color: 'blue'
	})}
	description={""}
	xAxisLabels={JSON.stringify(["Subday", "Monday", "Tuesday", "Wednesday", "Thursday"])}
  roundValues={true}
/>
```

<a id="piechart"></a>
## PieChart

![rn-chart-desk React Native Pie Chart](assets/piechart.png "rn-chart-desk PieChart")

```js
import { PieChart } from "rn-chart-desk";

// ...

<PieChart
	style={{ flex: 1 }}
	data={JSON.stringify([
		{
			value: 5,
			label: "Label 1"
		},
		{
			value: 7,
			label: "Label 2"
		},
		{
			value: 20,
			label: "Label 3"
		},
	])}
	description={"Pie chart for data"}
	roundValues={true}
/>
```

<a id="barchart"></a>
## BarChart

![rn-chart-desk React Native Bar Chart](assets/barchart.png "rn-chart-desk BarChart")

```js
import { BarChart } from "rn-chart-desk";

// ...

<BarChart
	style={{ flex: 1 }}
	data={JSON.stringify({
			values: [34, 2, 15, 29, 5],
			label: "Second Test",
			color: "blue"
		},
	)}
	description={"Bar chart for data"}
	xAxisLabels={JSON.stringify(["Jan", "Feb", "Mar", "Apr", "May"])}
	roundValues={false}
/>
```

<a id="grouped-barchart"></a>
## GroupedBarChart

![rn-chart-desk React Native Grouped Bar Chart](assets/grouped-barchart.png "rn-chart-desk GroupedBarChart")

```js
import { GroupedBarChart } from "rn-chart-desk";

// ...

<GroupedBarChart
  style={{ flex: 1 }}
  data={JSON.stringify([
    {
      values: [3, 2, 9, 10, 3],
      label: "First Dataset",
      color: "#F06F00"
    },
    {
      values: [5, 20, 7, 1, 34],
      label: "Second Dataset",
      color: 'blue'
    },
    {
      values: [30, 2, 15, 29, 5],
      label: "Third Dataset",
      color: 'green'
    },
  ])}
  description={""}
  xAxisLabels={JSON.stringify(["Jan", "Feb", "Mar", "Apr", "May"])}
  roundValues={true}
/>
```

## Remove decimal places on chart values
You can round all values on any of the charts by setting `roundValues` to `true` or `false`
```js
roundValues={true | false}
```

<a id="author"></a>
## Author
[Babalola Macaulay](https://github.com/devbabs)

## Cheers 🥂

## Acknowlegement
This package was made possible, thanks to being able to leverage and build on top of the amazing work that was done on [MPAndroidChart](https://github.com/PhilJay/MPAndroidChart).

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT
