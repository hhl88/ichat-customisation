import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';


@Component({
  selector: 'app-image-upload',
  templateUrl: './image-upload.component.html',
  styleUrls: ['./image-upload.component.scss']
})
export class ImageUploadComponent implements OnInit {
  @Input() selectedImage: string;
  fileName: string;
  @Output() onImageChanged = new EventEmitter();

  constructor() {
  }

  ngOnInit() {
    if (!this.fileName) {
      this.fileName = 'Select file ...';
    }
  }

  processFile(imageInput: any) {
    const file: File = imageInput.files[0];
    const reader = new FileReader();

    reader.addEventListener('load', (event: any) => {
      // this.selectedImage = event.target.result;
      // console.log('aawda', this.selectedImage);
      const img = new Image();
      img.src = event.target.result;
      const elem = document.createElement('canvas');

      const width = 600;
      const scaleFactor = width / img.width;
      console.log('scaldeFactor', scaleFactor);
      console.log('img', img);

      elem.width = width;
      elem.height = img.height * scaleFactor;
      const ctx = elem.getContext('2d');

      ctx.drawImage(img, 0, 0, width, img.height * scaleFactor);
      // // const width = 150;
      // // const height = 150;
      // elem.width = width;
      // elem.height = height;
      // ctx.drawImage(img, 0, 0, width, height);
      // this.selectedImage = ctx.canvas.toDataURL(img.src, 'image/png', 0.7).replace('image/png', 'image/octet-stream');
      // if (!HTMLCanvasElement.prototype.toBlob) {
      //   Object.defineProperty(HTMLCanvasElement.prototype, 'toBlob', {
      //     value: function (callback, type, quality) {
      //       const dataURL = this.toDataURL(type, quality).split(',')[1];
      //       setTimeout(function () {
      //         const binStr = atob(dataURL),
      //           len = binStr.length,
      //           arr = new Uint8Array(len);
      //         for (let i = 0; i < len; i++) {
      //           arr[i] = binStr.charCodeAt(i);
      //         }
      //         callback(new Blob([arr], {type: type || 'image/png'}));
      //       });
      //     }
      //   });
      // }
      ctx.canvas.toBlob((blob) => {
        console.log(blob); //output image as a blob
        const newFile = new File([blob], file.name, {
          type: 'image/png',
          lastModified: Date.now()
        }); // output image as a file

        const reader2 = new FileReader();
        reader2.addEventListener('load', (e: any) => {
          this.selectedImage = e.target.result;
          this.onImageChanged.emit({image: newFile});

        });

        reader2.readAsDataURL(newFile);

      }, 'image/png', 0.6);
      this.fileName = file.name;


    });

    reader.readAsDataURL(file);
  }

}

