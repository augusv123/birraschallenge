import { Deserializable } from './Deserializable';

export class Weather implements Deserializable {
main:any
deserialize(input: any) {
    Object.assign(this, input);
    return this;
  }

 }