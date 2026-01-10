import { Component, Input } from '@angular/core';
import { RouterLink } from "@angular/router";

@Component({
  selector: 'app-sidebar-menu',
  imports: [RouterLink],
  templateUrl: './sidebar-menu.html',
  styleUrl: './sidebar-menu.scss',
})
export class SidebarMenu {
  @Input({alias: 'hidden', required: true}) hidden = false;
}
