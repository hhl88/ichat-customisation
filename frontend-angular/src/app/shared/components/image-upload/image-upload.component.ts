import {Component, EventEmitter, OnInit, Output} from '@angular/core';


@Component({
  selector: 'app-image-upload',
  templateUrl: './image-upload.component.html',
  styleUrls: ['./image-upload.component.scss']
})
export class ImageUploadComponent implements OnInit {
  selectedImage: string;
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
      this.selectedImage = event.target.result;
      this.onImageChanged.emit({image: file});
      this.fileName = file.name;
    });

    reader.readAsDataURL(file);
  }

}

