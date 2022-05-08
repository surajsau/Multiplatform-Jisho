//
//  NoteEntryModal.swift
//  iosApp
//
//  Created by Suraj GP on 08/05/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

struct NoteEntryModal: View {
    
    @Binding var noteText: String
    
    var body: some View {
        ZStack(alignment: .top) {
            Color.Neomorph.main
                .edgesIgnoringSafeArea(.all)
            
            NoteTextField(noteText: self.$noteText, isEnabled: true, hint: "Add Note")
            .padding()
        }
    }
}

struct NoteEntryModalPreview: PreviewProvider {
        
    static var previews: some View {
        NoteEntryModal(noteText: .constant(""))
    }
}
