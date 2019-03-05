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
    this.fileName = file.name;
    const fileName = file.name;
    const reader = new FileReader();
    reader.addEventListener('load', (readerEvent: any) => {
      const rs = readerEvent.target.result;
      this.selectedImage = rs;
      const img = new Image();
      img.src = rs;

      img.addEventListener('load', (imgEvent: any) => {
        const elem = document.createElement('canvas');
        elem.width = img.width;
        elem.height = img.height;
        const ctx = elem.getContext('2d');
        // img.width and img.height will contain the original dimensions
        ctx.drawImage(img, 0, 0, img.width, img.height);
        ctx.canvas.toBlob((blob) => {
          const newFile = new File([blob], fileName, {
            type: 'image/png',
            lastModified: Date.now()
          });
          this.onImageChanged.emit({image: newFile, src: rs});
        }, 'image/png', 0.7);
      });
    });
    reader.readAsDataURL(file);
  }

}

