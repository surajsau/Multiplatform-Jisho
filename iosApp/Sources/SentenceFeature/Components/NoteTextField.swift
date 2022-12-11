//
//  NoteTextField.swift
//  
//
//  Created by surajsau on 2022/12/11.
//

import SwiftUI

struct NoteTextField: View {
    
    @Binding var noteText: String
    
    let isEnabled: Bool
    let hint: String
    
    var body: some View {
        VStack {
            TextField(self.hint, text: self.$noteText)
                .disabled(!self.isEnabled)
        }
        .padding()
    }
}

struct NoteTextField_Previews: PreviewProvider {
    static var previews: some View {
        ZStack {
            NoteTextField(noteText: .constant(""), isEnabled: false, hint: "Add note")
        }
    }
}
