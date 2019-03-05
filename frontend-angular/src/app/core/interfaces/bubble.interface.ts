
export interface Bubble {
  backgroundColor: string;
  borderColor: string;
  borderRadius: string;
  color: string;
}

export const MyBubbleDefault: Bubble = {
  backgroundColor: '#0084ff',
  borderColor: 'white',
  borderRadius: '3px',
  color: 'white'
};


export const TheirBubbleDefault: Bubble = {
  backgroundColor: '#a8a8a8',
  borderColor: 'white',
  borderRadius: '3px',
  color: 'black'
};

export const SystemBubbleDefault: Bubble = {
  backgroundColor: '#f1f0f0',
  borderColor: 'white',
  borderRadius: '3px',
  color: 'black'
};
