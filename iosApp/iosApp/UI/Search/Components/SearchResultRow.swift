//
//  SearchResult.swift
//  iosApp
//
//  Created by Suraj GP on 28/04/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

struct SearchResultRow: View {
    
    let model: SearchResult
    
    var body: some View {
        VStack(alignment: .leading) {
            VStack(alignment: .leading, spacing: 4) {
                
                HStack(alignment: .bottom, spacing: 6) {
                    Text(self.model.value)
                        .fontWeight(.semibold)
                        .font(.system(size: 18))
                    
                    Text(self.model.reading)
                        .font(.system(size: 14))
                    
                    Spacer()
                }
                
                Text(self.model.meanings)
                    .font(.system(size: 16))
                    .opacity(0.5)
            }
            .padding()
            .background(
                RoundedRectangle(cornerRadius: 8)
                    .fill(Color.Neomorph.main)
                    .softOuterShadow(darkShadow: Color.Neomorph.darkShadow, lightShadow: Color.Neomorph.lightShadow, offset: 4)
            )
            .padding(.horizontal, 16)
        }
    }
}
