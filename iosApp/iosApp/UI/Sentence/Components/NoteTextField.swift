//
//  NoteTextField.swift
//  iosApp
//
//  Created by Suraj GP on 08/05/22.
//  Copyright © 2022 orgName. All rights reserved.
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
        .background(
            RoundedRectangle(cornerRadius: 8).fill(Color.Neomorph.main)
                .softInnerShadow(RoundedRectangle(cornerRadius: 8), darkShadow: Color.Neomorph.darkShadow, lightShadow: Color.Neomorph.lightShadow, spread: 0.5, radius: 2)
        )
    }
}

struct NoteTextField_Previews: PreviewProvider {
    static var previews: some View {
        ZStack {
            Color.Neomorph.main
                .edgesIgnoringSafeArea(.all)

            NoteTextField(noteText: .constant(""), isEnabled: false, hint: "Add note")
                .padding()
        }
    }
}
