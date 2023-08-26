import * as React from 'react';

import { Dimensions, StyleSheet, View } from 'react-native';
import { PieChart, BarChart, LineChart, GroupedBarChart } from 'rn-chart-desk';

export default function App() {
  return (
    <View style={styles.container}>
    <View style={{ width: Dimensions.get('window').width - 40, height: Dimensions.get('window').height - 200 }}>
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
          description={""}
          roundValues={true}
      />
      <GroupedBarChart
          style={{ flex: 1 }}
          data={JSON.stringify([
              {
                  values: [3, 2, 9, 10, 3],
                  label: "First Reality",
                  color: "#F06F00"
              },
              {
                  values: [30, 2, 15, 29, 5],
                  label: "Second Test",
                  color: 'blue'
              },
          ])}
          description={""}
          xAxisLabels={JSON.stringify(["Jan", "Feb", "Mar", "Apr", "May"])}
          roundValues={true}
      />
      <LineChart
          style={{ flex: 1 }}
          data={JSON.stringify({
                  values: [34, 2, 15, 29, 5],
                  label: "Second Test",
                  color: 'blue'
              },
          )}
          description={""}
          xAxisLabels={JSON.stringify(["Subday", "Monday", "Tuesday", "Wednesday", "Thursday"])}
          roundValues={true}
      />
      <BarChart
        style={{ flex: 1 }}
        data={JSON.stringify({
                values: [34, 2, 15, 29, 5],
                label: "Second Test",
                color: 'blue'
            },
        )}
        description={""}
        xAxisLabels={JSON.stringify(["Jan", "Feb", "Mar", "Apr", "May"])}
        roundValues={false}
      />
    </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
