import {
  animate,
  query,
  style,
  transition,
  trigger,
  stagger,
  sequence
} from '@angular/animations';
import {AnimationsService} from './animations.service';
import {ROUTE_ANIMATIONS_ELEMENTS} from 'core/animations/route.animation';

export const ONCHANGE_ANIMATIONS_ELEMENTS = 'onchange-animations-elements';

const STEPS_ALL: any[] = [
  query(':enter', style({opacity: 0, position: 'fixed'}), {
    optional: true
  }),
  sequence([
    query(
      ':leave',
      [
        style({transform: 'translateY(0%)', opacity: 1,}),
        animate(
          '200ms ease-in-out',
          style({transform: 'translateY(-3%)', opacity: 0})
        ),
        style({position: 'fixed'})
      ],
      {optional: true}
    ),
    query(
      ':enter',
      [
        style({
          transform: 'translateY(-3%)',
          opacity: 0,
          position: 'static'
        }),
        animate(
          '.5s 200ms ease-in-out',
          style({transform: 'translateY(0%)', opacity: 1})
        )
      ],
      {optional: true}
    )
  ]),

];
const STEPS_NONE = [];
const STEPS_PAGE = [STEPS_ALL[0], STEPS_ALL[2]];
const STEPS_ELEMENTS = [STEPS_ALL[1], STEPS_ALL[3]];

export const onchangeAnimations = trigger('onchangeAnimations', [
  transition('* <=> *', STEPS_ALL),
  //transition(isRouteAnimationsNone, STEPS_NONE),
  //transition(isRouteAnimationsPage, STEPS_PAGE),
  //transition(isRouteAnimationsElements, STEPS_ELEMENTS)
]);

export function isRouteAnimationsAll() {
  return AnimationsService.isRouteAnimationsType('ALL');
}

export function isRouteAnimationsNone() {
  return AnimationsService.isRouteAnimationsType('NONE');
}

export function isRouteAnimationsPage() {
  return AnimationsService.isRouteAnimationsType('PAGE');
}

export function isRouteAnimationsElements() {
  return AnimationsService.isRouteAnimationsType('ELEMENTS');
}
