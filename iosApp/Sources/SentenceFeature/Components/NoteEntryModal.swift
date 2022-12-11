//
//  NoteEntryModal.swift
//  
//
//  Created by surajsau on 2022/12/11.
//

import SwiftUI

struct NoteEntryModal: View {
    
    @Binding var noteText: String
    
    var body: some View {
        ZStack(alignment: .top) {
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
