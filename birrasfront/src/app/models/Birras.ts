import { Deserializable } from './Deserializable';

export class Birras implements Deserializable {
main:any
deserialize(input: any) {
    Object.assign(this, input);
    return this;
  }

 }