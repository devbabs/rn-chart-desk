import * as React from 'react';

import { Dimensions, ScrollView, StyleSheet, View } from 'react-native';
import { PieChart, BarChart, LineChart, GroupedBarChart } from 'rn-chart-desk';

export default function App() {
  return (
    <ScrollView style={styles.container} contentContainerStyle={{ paddingVertical: 50, paddingHorizontal: 20 }}>
      <View
        style={{height: Dimensions.get('window').height + 200 }}
      >
          <PieChart
            style={{ height: 200, marginBottom: 50 }}
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
            style={{ height: 200, marginBottom: 50 }}
            data={JSON.stringify([
                {
                    values: [3, 2, 9, 10, 3],
                    label: "First Reality",
                    color: "#F06F00"
                },
                {
                    values: [30, 2, 15, 29, 5],
                    label: "Second Test",
                    color: 'teal'
                },
                {
                  values: [5, 20, 7, 1, 34],
                  label: "Third Dataset",
                  color: 'black'
                },
                {
                  values: [23, 17, 0, 8, 49],
                  label: "Fourth Dataset",
                  color: 'grey'
                },
            ])}
            description={""}
            xAxisLabels={JSON.stringify(["Jan", "Feb", "Mar", "Apr", "May"])}
            roundValues={true}
        />
        <LineChart
            style={{ height: 200, marginBottom: 50 }}
            data={JSON.stringify({
                    values: [34, 2, 15, 29, 5],
                    label: "Second Test",
                    color: 'grey'
                },
            )}
            description={""}
            xAxisLabels={JSON.stringify(["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday"])}
            roundValues={true}
        />
        <BarChart
          style={{ flex: 1 }}
          data={JSON.stringify({
                  values: [34, 2, 15, 29, 5],
                  label: "Second Test",
                  color: 'teal'
              },
          )}
          description={""}
          xAxisLabels={JSON.stringify(["Jan", "Feb", "Mar", "Apr", "May"])}
          roundValues={false}
        />
      </View>
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  container: {
    // flex: 1,
    // alignItems: 'center',
    // justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
