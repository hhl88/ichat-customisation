import {Bubble, MyBubbleDefault, SystemBubbleDefault, TheirBubbleDefault} from 'core/interfaces/bubble.interface';

export interface BubbleStyle {
  myBubble: Bubble;
  theirBubble: Bubble;
  systemBubble: Bubble;
}

export const BubbleStyleDefault: BubbleStyle = {
  myBubble: MyBubbleDefault,
  theirBubble: TheirBubbleDefault,
  systemBubble: SystemBubbleDefault,
};
