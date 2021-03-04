import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { I${ucomp} } from 'app/shared/model/${comp}.model';
import { ${ucomp}Service } from './${comp}.service';

@Component({
  templateUrl: './${comp}-delete-dialog.component.html',
})
export class ${ucomp}DeleteDialogComponent {
  ${comp}?: I${ucomp};

  constructor(protected ${comp}Service: ${ucomp}Service, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.${comp}Service.delete(id).subscribe(() => {
      this.eventManager.broadcast('${comp}ListModification');
      this.activeModal.close();
    });
  }
}
