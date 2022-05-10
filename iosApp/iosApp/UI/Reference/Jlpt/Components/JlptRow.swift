//
//  JlptRow.swift
//  iosApp
//
//  Created by Suraj GP on 10/05/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct JlptRow: View {
    
    let title: String
    let description: String
    
    var body: some View {
        HStack {
            VStack(alignment: .leading, spacing: 8) {
                Text(self.title)
                    .font(.title3)
                    .fontWeight(.semibold)
                    .foregroundColor(Color.Neomorph.text)
                
                Text(self.description)
                    .font(.body)
                    .foregroundColor(Color.Neomorph.text)
                    .opacity(0.6)
            }
            Spacer()
        }
        .padding()
        .neomorph()
    }
}

struct JlptRow_Previews: PreviewProvider {
    static var previews: some View {
        ZStack {
            Color.Neomorph.main
                .edgesIgnoringSafeArea(.all)
            
            JlptRow(title: "N5", description: "Understanding of some basic Japanese")
                .padding()
        }
    }
}
