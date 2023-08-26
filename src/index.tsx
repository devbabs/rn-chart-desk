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

type RnChartDeskProps = {
  color: string;
  style: ViewStyle;
};

const ComponentName = 'RnChartDeskView';

export const RnChartDeskView =
  UIManager.getViewManagerConfig(ComponentName) != null
    ? requireNativeComponent<RnChartDeskProps>(ComponentName)
    : () => {
        throw new Error(LINKING_ERROR);
      };
