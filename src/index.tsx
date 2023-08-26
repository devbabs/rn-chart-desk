import {
  requireNativeComponent,
  UIManager,
  Platform,
  type ViewStyle,
} from 'react-native';

const LINKING_ERROR =
  `The package 'rn-chart-desk' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

export const PieChart =
  UIManager.getViewManagerConfig('PieChart') != null
    ? requireNativeComponent<{
      data: string,
      description: string,
      style: ViewStyle;
      roundValues: boolean
    }>('PieChart')
    : () => {
        throw new Error(LINKING_ERROR);
      };

export const LineChart = requireNativeComponent<{
  data: string,
  description: string,
  style: ViewStyle;
  xAxisLabels: string,
  roundValues: boolean
}>('LineChart')

export const BarChart = requireNativeComponent<{
  data: string,
  description: string,
  style: ViewStyle;
  xAxisLabels: string,
  roundValues: boolean
}>('BarChart')

export const GroupedBarChart = requireNativeComponent<{
  data: string,
  description: string,
  style: ViewStyle;
  xAxisLabels: string,
  roundValues: boolean
}>('GroupedBarChart')
